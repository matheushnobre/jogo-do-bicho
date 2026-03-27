package br.com.bichofull.bichofull.service.bet;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.dtos.bets.BetResultDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.dtos.bets.HistoryBetDTO;
import br.com.bichofull.bichofull.mapper.BetMapper;
import br.com.bichofull.bichofull.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryBetsService {

    @Autowired
    BetRepository betRepository;

    @Autowired
    BetMapper betMapper;

    public HistoryBetDTO getMyBets(User user, int pageNumber){
        Page<Bet> pages = betRepository.findAllByUser(user, PageRequest.of(pageNumber, 10, Sort.by("betDate").descending()));
        List<BetResultDTO> bets = pages.get().map(betMapper::toDTO).toList();
        return new HistoryBetDTO(bets, pages.getTotalElements(), pages.getTotalPages());
    }

}
