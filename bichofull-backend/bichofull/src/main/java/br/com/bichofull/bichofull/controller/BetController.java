package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetPostDTO;
import br.com.bichofull.bichofull.domain.bet.BetResultDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.service.bet.HistoryBetsService;
import br.com.bichofull.bichofull.service.bet.PlaceBetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/bets")
@CrossOrigin("*")
public class BetController {

    @Autowired
    PlaceBetService placeBetService;

    @Autowired
    HistoryBetsService historyBetsService;

    @PostMapping("/bet")
    public ResponseEntity<BetResultDTO> bet(@AuthenticationPrincipal User user, @RequestBody @Valid BetPostDTO data) {
        Bet bet = placeBetService.placeBet(user, data);
        return ResponseEntity.ok(new BetResultDTO(bet));
    }

    @GetMapping("/my-bets")
    public ResponseEntity<List<BetResultDTO>> getMyBets(@AuthenticationPrincipal User user){
        List<Bet> bets = historyBetsService.getMyBets(user);
        List<BetResultDTO> betsDTO = new ArrayList<>();

        for(Bet bet : bets){
            betsDTO.add(new BetResultDTO(bet));
        }

        return ResponseEntity.ok(betsDTO);
    }
}
