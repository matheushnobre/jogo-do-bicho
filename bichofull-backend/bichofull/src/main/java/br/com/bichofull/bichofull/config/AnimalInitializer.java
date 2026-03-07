package br.com.bichofull.bichofull.config;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimalInitializer implements CommandLineRunner {

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public void run(String... args)  {
        if(animalRepository.count() > 0){
            return;
        }

        ArrayList<Animal> animals = getAnimals();
        animalRepository.saveAll(animals);
    }

    private ArrayList<Animal> getAnimals(){
        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(new Animal(1, "Avestruz"));
        animals.add(new Animal(2, "Águia"));
        animals.add(new Animal(3, "Burro"));
        animals.add(new Animal(4, "Borboleta"));
        animals.add(new Animal(5, "Cachorro"));
        animals.add(new Animal(6, "Cabra"));
        animals.add(new Animal(7, "Carneiro"));
        animals.add(new Animal(8, "Camelo"));
        animals.add(new Animal(9, "Cobra"));
        animals.add(new Animal(10, "Coelho"));
        animals.add(new Animal(11, "Cavalo"));
        animals.add(new Animal(12, "Elefante"));
        animals.add(new Animal(13, "Galo"));
        animals.add(new Animal(14, "Gato"));
        animals.add(new Animal(15, "Jacaré"));
        animals.add(new Animal(16, "Leão"));
        animals.add(new Animal(17, "Macaco"));
        animals.add(new Animal(18, "Porco"));
        animals.add(new Animal(19, "Pavão"));
        animals.add(new Animal(20, "Peru"));
        animals.add(new Animal(21, "Touro"));
        animals.add(new Animal(22, "Tigre"));
        animals.add(new Animal(23, "Urso"));
        animals.add(new Animal(24, "Veado"));
        animals.add(new Animal(25, "Vaca"));

        return animals;
    }
}
