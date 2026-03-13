package br.com.bichofull.bichofull.domain.bet;

import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.results.ResultDTO;

import java.math.BigDecimal;

public record BetResultDTO(
        String userName,
        String betType,
        int betNumber,
        ResultDTO result,
        BigDecimal payout
) {

    public BetResultDTO(Bet bet) {
        this(
                bet.getUser().getName(),
                bet.getBetType().name(),
                bet.getBetNumber(),
                new ResultDTO(bet.getResult()),
                bet.getPayout()
        );
    }
}