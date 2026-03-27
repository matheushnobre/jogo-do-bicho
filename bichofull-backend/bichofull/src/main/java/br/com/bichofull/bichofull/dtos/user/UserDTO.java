package br.com.bichofull.bichofull.dtos.user;

import br.com.bichofull.bichofull.domain.user.User;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String email,
        BigDecimal balance
) {

    public UserDTO(User user) {
        this(user.getName(), user.getEmail(), user.getBalance());
    }
}