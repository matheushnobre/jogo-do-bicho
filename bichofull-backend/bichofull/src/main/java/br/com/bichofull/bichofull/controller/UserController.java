package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.deposit.DepositDTO;
import br.com.bichofull.bichofull.domain.deposit.DepositResponseDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.domain.user.UserDTO;
import br.com.bichofull.bichofull.service.deposit.DepositService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    DepositService depositService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDTO> deposit(@AuthenticationPrincipal User user, @RequestBody @Valid DepositDTO data){
        BigDecimal newBalance = depositService.deposit(user, data.value());
        return ResponseEntity.ok(new DepositResponseDTO(newBalance));
    }
}
