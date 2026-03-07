package br.com.bichofull.bichofull.repository;

import br.com.bichofull.bichofull.domain.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
