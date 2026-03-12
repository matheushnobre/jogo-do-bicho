package br.com.bichofull.bichofull.repository;

import br.com.bichofull.bichofull.domain.results.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
