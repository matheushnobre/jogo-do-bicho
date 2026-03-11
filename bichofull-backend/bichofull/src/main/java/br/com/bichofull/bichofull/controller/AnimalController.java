package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.repository.AnimalRepository;
import br.com.bichofull.bichofull.service.AnimalService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/animals")
@CrossOrigin("*")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping()
    public ResponseEntity<List<Animal>> getAnimals(){
        List<Animal> animals = animalService.getAnimals();
        return ResponseEntity.ok().body(animals);
    }
}
