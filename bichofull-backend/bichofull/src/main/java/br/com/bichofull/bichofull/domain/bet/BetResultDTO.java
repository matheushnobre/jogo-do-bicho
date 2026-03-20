package br.com.bichofull.bichofull.domain.bet;

import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.results.ResultDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BetResultDTO(
        Long id,
        BigDecimal betAmount,
        String userName,
        String betType,
        int betNumber,
        ResultDTO result,
        BigDecimal payout,
        String betDate
) {

    public BetResultDTO(Bet bet) {
        this(
                bet.getId(),
                bet.getBetAmount(),
                bet.getUser().getName(),
                bet.getBetType().name(),
                bet.getBetNumber(),
                new ResultDTO(bet.getResult()),
                bet.getPayout(),
                bet.getBetDate().toString()
        );
    }
}