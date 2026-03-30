package br.com.bichofull.bichofull.service.bet;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.dtos.bets.BetPostDTO;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.repository.BetRepository;
import br.com.bichofull.bichofull.repository.UserRepository;
import br.com.bichofull.bichofull.service.results.ResultService;
import br.com.bichofull.bichofull.utils.CheckBet;
import br.com.bichofull.bichofull.validators.BetValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class PlaceBetService {

    @Autowired
    BetRepository betRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResultService resultService;

    @Autowired
    CheckBet checkBet;

    @Autowired
    BetValidator betValidator;

    @Transactional
    public Bet placeBet(User user, BetPostDTO data ) {
        betValidator.validateBet(user, data);

        Bet bet = createBet(user, data);
        user.setBalance(user.getBalance().subtract(data.betAmount()));

        Result result = resultService.generateResults(data.betType());
        bet.setResult(result);

        BigDecimal payout = checkBet.check(bet);
        bet.setPayout(payout);
        user.setBalance(user.getBalance().add(payout));

        userRepository.save(user);
        betRepository.save(bet);

        return bet;
    }

    private Bet createBet(User user, BetPostDTO data){
        Bet bet = new Bet();
        bet.setUser(user);
        bet.setBetNumber(data.betNumber());
        bet.setBetAmount(data.betAmount());
        bet.setBetType(data.betType());
        bet.setBetDate(Instant.now());

        return bet;
    }

}
