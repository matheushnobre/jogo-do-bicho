package br.com.bichofull.bichofull.repository;

import br.com.bichofull.bichofull.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
