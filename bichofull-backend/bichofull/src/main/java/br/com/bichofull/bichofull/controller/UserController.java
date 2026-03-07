package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.domain.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(new UserDTO(user));
    }
}
