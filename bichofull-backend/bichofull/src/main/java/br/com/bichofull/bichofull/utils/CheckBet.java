package br.com.bichofull.bichofull.utils;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.results.Result;

import java.math.BigDecimal;

public class CheckBet {

    public static BigDecimal check(Bet bet){
        Result result = bet.getResult();

        // Checks if wins in main draw
        if(result.getMainDraw().getNumber() == bet.getBetNumber()){
            return bet.getBetAmount().multiply(bet.getBetType().getWinMainDraw());
        }

        // Checks if wins in one of the secondary draws
        for(int i=0; i<result.getSecondaryDraws().size(); i++){
            if(result.getSecondaryDraws().get(i).getNumber() == bet.getBetNumber()){
                return bet.getBetAmount().multiply(bet.getBetType().getWinSecondDraw());
            }
        }

        return BigDecimal.ZERO;
    }
}
