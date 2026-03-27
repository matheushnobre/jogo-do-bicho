package br.com.bichofull.bichofull.dtos.deposit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DepositDTO(
        @Positive
        @NotNull
        BigDecimal value
) {
}
