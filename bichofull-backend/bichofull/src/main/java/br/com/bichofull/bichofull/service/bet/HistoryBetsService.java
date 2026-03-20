package br.com.bichofull.bichofull.service.bet;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryBetsService {

    @Autowired
    BetRepository betRepository;

    public List<Bet> getMyBets(User user){
        List<Bet> bets = betRepository.findByUserOrderByBetDateDesc(user);

        return bets;
    }

}
