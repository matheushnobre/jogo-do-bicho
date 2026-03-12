package br.com.bichofull.bichofull.repository;

import br.com.bichofull.bichofull.domain.bet.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
