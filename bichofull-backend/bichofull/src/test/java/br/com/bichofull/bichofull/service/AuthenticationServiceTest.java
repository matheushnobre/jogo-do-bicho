package br.com.bichofull.bichofull.service;

import br.com.bichofull.bichofull.domain.user.AuthenticationDTO;
import br.com.bichofull.bichofull.domain.user.RegisterDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.exception.custom.EmailAlreadyRegisteredException;
import br.com.bichofull.bichofull.infra.security.TokenService;
import br.com.bichofull.bichofull.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    @DisplayName("Should return a token when credentials are valid")
    void loginOk() {
        var dto = new AuthenticationDTO("test@gmail.com", "12345678");
        var user = new User("Test", "test@gmail.com", "12345678");
        user.setId(1L);
        var authentication = mock(Authentication.class);
        var httpResponse = mock(HttpServletResponse.class);


        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(user))
                .thenReturn("fake-jwt-token");

        var response = authenticationService.login(dto, httpResponse);
        assertNotNull(response);
        assertEquals("fake-jwt-token", response.token());
        verify(httpResponse).addCookie(any(Cookie.class));
    }

    @Test
    @DisplayName("Should throw BadCredentialsException when credentials are invalid")
    void loginInvalidCredentials() {
        var dto = new AuthenticationDTO("wrong@gmail.com", "12345678");
        var httpResponse = mock(HttpServletResponse.class);

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class,
                () -> authenticationService.login(dto, httpResponse));

        verify(tokenService, never()).generateToken(any());
    }

    @Test
    @DisplayName("Should create user when data is valid")
    void registerOk() {
        var dto = new RegisterDTO("Test", "test@gmail.com", "12345678");

        when(userRepository.existsByEmail(any()))
                .thenReturn(false);

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User saved = invocation.getArgument(0);
                    saved.setId(1L);
                    return saved;
                });

        var response = authenticationService.register(dto);
        assertNotNull(response);
        assertEquals("Test", response.getName());
        assertEquals("test@gmail.com", response.getEmail());
        assertTrue(new BCryptPasswordEncoder().matches("12345678", response.getPassword()));
        assertEquals(new BigDecimal("1000.00"), response.getBalance());
    }

    @Test
    @DisplayName("Should throw EmailAlreadyRegisteredException when email already exists")
    void registerInvalidEmail() {
        var dto = new RegisterDTO("WrongTest", "wrong@gmail.com", "12345678");
        when(userRepository.existsByEmail(dto.email()))
                .thenReturn(true);

        assertThrows(EmailAlreadyRegisteredException.class,
                () -> authenticationService.register(dto));

        verify(userRepository, never()).save(any());
    }
}