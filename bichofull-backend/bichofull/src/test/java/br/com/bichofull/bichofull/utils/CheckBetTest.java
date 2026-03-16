package br.com.bichofull.bichofull.utils;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.results.Draw;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.helpers.GenerateBet;
import br.com.bichofull.bichofull.helpers.GenerateResults;
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




    @Test
    @DisplayName("Should return 0")
    void loseBet() {
        Bet bet = GenerateBet.generateBet();
        bet.setBetNumber(25);

        BigDecimal payout = CheckBet.check(bet);
        assertEquals(BigDecimal.ZERO, payout);
    }

    @Test
    @DisplayName("Should return betType.getWinMainDraw() * bet.getAmount()")
    void winMainDraw(){
        Bet bet = GenerateBet.generateBet();
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
        Bet bet = GenerateBet.generateBet();
        bet.setBetNumber(4);
        bet.setBetType(BetType.GROUP);
        bet.setBetAmount(new BigDecimal("5.00"));

        BigDecimal expected = bet.getBetAmount().multiply(bet.getBetType().getWinSecondDraw());
        BigDecimal payout = CheckBet.check(bet);

        assertEquals(expected, payout);
    }
}