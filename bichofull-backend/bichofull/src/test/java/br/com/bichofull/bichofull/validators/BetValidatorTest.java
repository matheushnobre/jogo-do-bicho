package br.com.bichofull.bichofull.validators;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetPostDTO;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.exception.custom.BetNumberInvalidException;
import br.com.bichofull.bichofull.exception.custom.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BetValidatorTest {

    private BetValidator betValidator;

    @BeforeEach
    void setup() {
        betValidator = new BetValidator();
    }

    @Test
    @DisplayName("Should throws BetNumberInvalidException when bets in an invalid GROUP")
    void betsInAnInvalidGroup() {
        User user = new User("Teste", "teste@gmail.com", "password");
        BetPostDTO data = new BetPostDTO(BetType.GROUP.getMaxNumber()+1, new BigDecimal("12.00"), BetType.GROUP);

        assertThrows(BetNumberInvalidException.class, () -> {
            betValidator.validateBet(user, data);
        });
    }

    @Test
    @DisplayName("Should throws BetNumberInvalidException when bets in an invalid DEZENA")
    void betsInAnInvalidDezena() {
        User user = new User("Teste", "teste@gmail.com", "password");
        BetPostDTO data = new BetPostDTO(BetType.DEZENA.getMaxNumber()+1, new BigDecimal("12.00"), BetType.DEZENA);

        assertThrows(BetNumberInvalidException.class, () -> {
            betValidator.validateBet(user, data);
        });
    }

    @Test
    @DisplayName("Should throws BetNumberInvalidException when bets in an invalid THOUSANDS")
    void betsInAnInvalidThousands() {
        User user = new User("Teste", "teste@gmail.com", "password");
        BetPostDTO data = new BetPostDTO(BetType.THOUSANDS.getMaxNumber()+1, new BigDecimal("12.00"), BetType.THOUSANDS);

        assertThrows(BetNumberInvalidException.class, () -> {
            betValidator.validateBet(user, data);
        });
    }

    @Test
    @DisplayName("Should return true when bets in a valid GROUP")
    void betsValidGroup() {
        User user = new User("Teste", "teste@gmail.com", "password");
        BetPostDTO data = new BetPostDTO(BetType.GROUP.getMaxNumber(), new BigDecimal("12.00"), BetType.GROUP);

        assertTrue(betValidator.validateBet(user, data));
    }

    @Test
    @DisplayName("Should throws InsufficientBalanceException when bets with insufficient balance")
    void betsWithBalanceNotOk() {
        User user = new User("Teste", "teste@gmail.com", "password");
        user.setBalance(new BigDecimal("1000.00"));
        BetPostDTO data = new BetPostDTO(BetType.GROUP.getMaxNumber(), new BigDecimal("1000.01"), BetType.GROUP);

        assertThrows(InsufficientBalanceException.class, () -> {
            betValidator.validateBet(user, data);
        });
    }

    @Test
    @DisplayName("Should return true when bets with sufficient balance")
    void betsWithBalanceOk() {
        User user = new User("Teste", "teste@gmail.com", "password");
        user.setBalance(new BigDecimal("1000.00"));
        BetPostDTO data = new BetPostDTO(BetType.GROUP.getMaxNumber(), new BigDecimal("12.00"), BetType.GROUP);

        assertTrue(betValidator.validateBet(user, data));
    }

    @Test
    @DisplayName("Should return true when bets with balance equals bet amount")
    void betsWithBalanceEqualsBetAmount() {
        User user = new User("Teste", "teste@gmail.com", "password");
        user.setBalance(new BigDecimal("1000.00"));
        BetPostDTO data = new BetPostDTO(BetType.GROUP.getMaxNumber(), new BigDecimal("1000.00"), BetType.GROUP);

        assertTrue(betValidator.validateBet(user, data));
    }
}