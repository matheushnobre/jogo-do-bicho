package br.com.bichofull.bichofull.service;

import br.com.bichofull.bichofull.domain.user.AuthenticationDTO;
import br.com.bichofull.bichofull.domain.user.LoginResponseDTO;
import br.com.bichofull.bichofull.domain.user.RegisterDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.exception.custom.EmailAlreadyRegisteredException;
import br.com.bichofull.bichofull.infra.security.TokenService;
import br.com.bichofull.bichofull.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public User register(RegisterDTO data){
        if(this.userRepository.existsByEmail(data.email())){
            throw new EmailAlreadyRegisteredException(data.email());
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(
                data.name(),
                data.email(),
                encryptedPassword
        );

        return userRepository.save(newUser);
    }
}
