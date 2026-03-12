package br.com.bichofull.bichofull.utils;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.repository.AnimalRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifyAnimalTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private VerifyAnimal verifyAnimal;

    @Test
    @DisplayName("Should return Avestruz when number equals 1 and betType equals GROUP")
    void verifyAnimalGroup() {
        Animal expectedAnimal = new Animal(1, "Avestruz");
        when(animalRepository.findById(1)).thenReturn(Optional.of(expectedAnimal));

        Animal result = verifyAnimal.verifyAnimal(1, BetType.GROUP);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Avestruz", result.getName());

        verify(animalRepository).findById(1);
    }

    @Test
    @DisplayName("Should return Avestruz when number equals 1 and betType equals DEZENA")
    void verifyAnimalDezenaWithNumber01(){
        Animal expectedAnimal = new Animal(1, "Avestruz");
        when(animalRepository.findById(1)).thenReturn(Optional.of(expectedAnimal));

        Animal result = verifyAnimal.verifyAnimal(1, BetType.DEZENA);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Avestruz", result.getName());

        verify(animalRepository).findById(1);
    }

    @Test
    @DisplayName("Should return Avestruz when number equals 4 and betType equals DEZENA")
    void verifyAnimalDezenaWithNumber04(){
        Animal expectedAnimal = new Animal(1, "Avestruz");
        when(animalRepository.findById(1)).thenReturn(Optional.of(expectedAnimal));

        Animal result = verifyAnimal.verifyAnimal(4, BetType.DEZENA);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Avestruz", result.getName());

        verify(animalRepository).findById(1);
    }

    @Test
    @DisplayName("Should return Vaca when number equals 100 and betType equals THOUSANDS")
    void verifyAnimalMilharWithNumber100(){
        Animal expectedAnimal = new Animal(25, "Vaca");
        when(animalRepository.findById(25)).thenReturn(Optional.of(expectedAnimal));

        Animal result = verifyAnimal.verifyAnimal(100, BetType.THOUSANDS);

        assertNotNull(result);
        assertEquals(25, result.getId());
        assertEquals("Vaca", result.getName());

        verify(animalRepository).findById(25);
    }
}