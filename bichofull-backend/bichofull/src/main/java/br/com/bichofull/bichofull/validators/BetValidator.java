package br.com.bichofull.bichofull.validators;

import br.com.bichofull.bichofull.domain.bet.BetPostDTO;
import br.com.bichofull.bichofull.domain.user.User;
import br.com.bichofull.bichofull.exception.custom.BetNumberInvalidException;
import br.com.bichofull.bichofull.exception.custom.InsufficientBalanceException;
import org.springframework.stereotype.Component;

@Component
public class BetValidator {

    public boolean validateBet(User user, BetPostDTO data){
        if(!data.isBetNumberValid()){
            throw new BetNumberInvalidException();
        }

        if(user.getBalance().compareTo(data.betAmount()) < 0){
            throw new InsufficientBalanceException();
        }

        return true;
    }
}
