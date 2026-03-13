package br.com.bichofull.bichofull.service.results;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.repository.DrawRepository;
import br.com.bichofull.bichofull.repository.ResultRepository;
import br.com.bichofull.bichofull.utils.VerifyAnimal;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    @Mock
    private DrawRepository drawRepository;

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private VerifyAnimal verifyAnimal;

    @InjectMocks
    private ResultService resultService;

    @Test
    void generateResults() {
        BetType betType = BetType.GROUP;

        when(verifyAnimal.verifyAnimal(anyInt(), any())).thenReturn(new Animal(1, "Avestruz"));

        Result result = resultService.generateResults(betType);
        assertNotNull(result.getMainDraw());
        assertNotNull(result.getSecondaryDraws());
        assertEquals(4, result.getSecondaryDraws().size());
    }
}