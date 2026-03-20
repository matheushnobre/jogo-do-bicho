package br.com.bichofull.bichofull.repository;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByUser(User user);
}
