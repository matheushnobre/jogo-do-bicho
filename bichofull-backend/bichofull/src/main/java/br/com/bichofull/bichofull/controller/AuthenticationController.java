package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.user.AuthenticationDTO;
import br.com.bichofull.bichofull.domain.user.LoginResponseDTO;
import br.com.bichofull.bichofull.domain.user.RegisterDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        return ResponseEntity.ok(authenticationService.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        User newUser = authenticationService.register(data);
        URI location = URI.create("/users/" + newUser.getId());

        return ResponseEntity.created(location).build();
    }
}
