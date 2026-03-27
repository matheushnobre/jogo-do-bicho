package br.com.bichofull.bichofull.dtos.bets;

import br.com.bichofull.bichofull.domain.bet.BetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BetPostDTO(
        @NotNull(message = "betNumber is mandatory")
        int betNumber,

        @Positive(message = "betAmount must be positive")
        @NotNull
        BigDecimal betAmount,

        @NotNull(message = "betType is mandatory")
        BetType betType
) {

    public boolean isBetNumberValid(){
        if(betType == null) return false;
        return betType.isNumberValid(betNumber);
    }
}
