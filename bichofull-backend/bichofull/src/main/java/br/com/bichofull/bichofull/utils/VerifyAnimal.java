package br.com.bichofull.bichofull.utils;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class VerifyAnimal {

    @Autowired
    private AnimalRepository animalRepository;

    public Animal verifyAnimal(int number, BetType betType){
        if(betType == BetType.GROUP) return animalRepository.findById(number).orElse(null);
        int lastTwoDigits = number % 100;

        if(lastTwoDigits == 0) return animalRepository.findById(25).orElse(null);

        int groupId = ((lastTwoDigits-1) / 4) + 1;
        return animalRepository.findById(groupId).orElse(null);
    }
}
