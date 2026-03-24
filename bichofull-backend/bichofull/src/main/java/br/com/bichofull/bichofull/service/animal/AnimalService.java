package br.com.bichofull.bichofull.service.animal;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    public List<Animal> getAnimals(){
        return animalRepository.findAll();
    }

}
