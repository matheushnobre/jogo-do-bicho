package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetPostDTO;
import br.com.bichofull.bichofull.domain.bet.BetResultDTO;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.service.bet.PlaceBetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bets")
@CrossOrigin("*")
public class BetController {

    @Autowired
    PlaceBetService placeBetService;

    @PostMapping("/bet")
    public ResponseEntity<BetResultDTO> bet(@AuthenticationPrincipal User user, @RequestBody @Valid BetPostDTO data) {
        Bet bet = placeBetService.placeBet(user, data);
        return ResponseEntity.ok(new BetResultDTO(bet));
    }
}
