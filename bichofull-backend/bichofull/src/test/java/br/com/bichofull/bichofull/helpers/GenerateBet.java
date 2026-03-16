package br.com.bichofull.bichofull.helpers;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.user.User;

import java.math.BigDecimal;

public class GenerateBet {

    public static Bet generateBet(){
        Bet bet = new Bet();
        bet.setBetAmount(new BigDecimal("5.00"));
        bet.setBetType(BetType.GROUP);
        bet.setBetNumber(1);
        bet.setResult(GenerateResults.generateResult());
        bet.setUser(new User("Test", "test@gmail.com", "password"));
        return bet;
    }
}
