package br.com.bichofull.bichofull.service.deposit;

import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositService {

    @Autowired
    UserRepository userRepository;

    public BigDecimal deposit(User user, BigDecimal value){
        user.setBalance(user.getBalance().add(value));
        userRepository.save(user);
        return user.getBalance();
    }
}
