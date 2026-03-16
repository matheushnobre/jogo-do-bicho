package br.com.bichofull.bichofull.service.bet;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetPostDTO;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.helpers.GenerateResults;
import br.com.bichofull.bichofull.repository.BetRepository;
import br.com.bichofull.bichofull.repository.UserRepository;
import br.com.bichofull.bichofull.service.results.ResultService;
import br.com.bichofull.bichofull.utils.CheckBet;
import br.com.bichofull.bichofull.validators.BetValidator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceBetServiceTest {

    @Mock
    BetRepository betRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ResultService resultService;

    @Mock
    BetValidator betValidator;

    @Mock
    CheckBet checkBet;

    @Captor
    ArgumentCaptor<Bet> betCaptor;

    @InjectMocks
    PlaceBetService placeBetService;

    @Test
    @DisplayName("Should alter balance and save")
    void verifyIfBalanceChangesWhenUserLoses() {
        User user = new User("test", "test@gmail.com", "test");
        user.setBalance(new BigDecimal("100.00"));
        BetPostDTO data = new BetPostDTO(24, new BigDecimal("5.00"), BetType.GROUP);

        when(betValidator.validateBet(user, data)).thenReturn(true);
        when(resultService.generateResults(any())).thenReturn(GenerateResults.generateResult());
        when(checkBet.check(any())).thenReturn(BigDecimal.ZERO);

        placeBetService.placeBet(user, data);

        assertEquals(new BigDecimal("95.00"), user.getBalance());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Should alter balance and save")
    void verifyIfBalanceChangesWhenUserWins() {
        User user = new User("test", "test@gmail.com", "test");
        user.setBalance(new BigDecimal("100.00"));
        BetPostDTO data = new BetPostDTO(24, new BigDecimal("5.00"), BetType.GROUP);

        BigDecimal payload = data.betAmount().multiply(BetType.GROUP.getWinMainDraw());
        when(betValidator.validateBet(user, data)).thenReturn(true);
        when(resultService.generateResults(any())).thenReturn(GenerateResults.generateResult());
        when(checkBet.check(any())).thenReturn(payload);

        placeBetService.placeBet(user, data);

        assertEquals(new BigDecimal("95.00").add(payload), user.getBalance());
        verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Should set result in bet")
    void setResult(){
        User user = new User("test", "test@gmail.com", "test");
        BetPostDTO data = new BetPostDTO(24, new BigDecimal("5.00"), BetType.GROUP);

        BigDecimal payload = data.betAmount().multiply(BetType.GROUP.getWinMainDraw());
        Result result = GenerateResults.generateResult();

        when(betValidator.validateBet(user, data)).thenReturn(true);
        when(resultService.generateResults(any())).thenReturn(result);
        when(checkBet.check(any())).thenReturn(payload);

        placeBetService.placeBet(user, data);

        verify(betRepository).save(betCaptor.capture());
        Bet capturedBet = betCaptor.getValue();
        assertEquals(result, capturedBet.getResult());
    }
}