package br.com.bichofull.bichofull.controller;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.exception.ApiError;
import br.com.bichofull.bichofull.infra.security.SecurityErrorDTO;
import br.com.bichofull.bichofull.service.animal.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Animals")
@RestController
@RequestMapping("api/animals")
@CrossOrigin("*")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Operation(
            summary = "Returns all animals.",
            description = "Returns a list with all animals data."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returns all animals data."
            )
    })
    @GetMapping()
    public ResponseEntity<List<Animal>> getAnimals(){
        List<Animal> animals = animalService.getAnimals();
        return ResponseEntity.ok().body(animals);
    }
}
