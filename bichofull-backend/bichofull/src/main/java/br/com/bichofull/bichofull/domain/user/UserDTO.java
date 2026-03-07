package br.com.bichofull.bichofull.domain.user;

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