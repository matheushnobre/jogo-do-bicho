package br.com.bichofull.bichofull.repository;

import br.com.bichofull.bichofull.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
