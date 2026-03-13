package br.com.bichofull.bichofull.utils;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.results.Draw;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CheckBetTest {

    private Result generateResult(){
        Draw mainDraw = new Draw();
        mainDraw.setNumber(1);

        List<Draw> secondaryDraws = new ArrayList<>();
        for(int i=2; i<=5; i++){
            Draw draw = new Draw();
            draw.setNumber(i);
            secondaryDraws.add(draw);
        }

        Result result = new Result();
        result.setMainDraw(mainDraw);
        result.setSecondaryDraws(secondaryDraws);

        return result;
    }

    private Bet generateBet(){
        Bet bet = new Bet();
        bet.setBetAmount(new BigDecimal("5.00"));
        bet.setBetType(BetType.GROUP);
        bet.setBetNumber(1);
        bet.setResult(generateResult());
        bet.setUser(new User("Test", "test@gmail.com", "password"));
        return bet;
    }

    @Test
    @DisplayName("Should return 0")
    void loseBet() {
        Bet bet = generateBet();
        bet.setBetNumber(25);

        BigDecimal payout = CheckBet.check(bet);
        assertEquals(BigDecimal.ZERO, payout);
    }

    @Test
    @DisplayName("Should return betType.getWinMainDraw() * bet.getAmount()")
    void winMainDraw(){
        Bet bet = generateBet();
        bet.setBetNumber(1);
        bet.setBetType(BetType.GROUP);
        bet.setBetAmount(new BigDecimal("5.00"));

        BigDecimal expected = bet.getBetAmount().multiply(bet.getBetType().getWinMainDraw());
        BigDecimal payout = CheckBet.check(bet);

        assertEquals(expected, payout);
    }

    @Test
    @DisplayName("Should return betType.getWinSecondaryDraw() * bet.getAmount()")
    void winSecondaryDraw(){
        Bet bet = generateBet();
        bet.setBetNumber(4);
        bet.setBetType(BetType.GROUP);
        bet.setBetAmount(new BigDecimal("5.00"));

        BigDecimal expected = bet.getBetAmount().multiply(bet.getBetType().getWinSecondDraw());
        BigDecimal payout = CheckBet.check(bet);

        assertEquals(expected, payout);
    }
}