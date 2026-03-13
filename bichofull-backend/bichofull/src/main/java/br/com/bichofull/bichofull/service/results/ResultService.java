package br.com.bichofull.bichofull.service.results;

import br.com.bichofull.bichofull.domain.animal.Animal;
import br.com.bichofull.bichofull.domain.bet.BetType;
import br.com.bichofull.bichofull.domain.results.Draw;
import br.com.bichofull.bichofull.domain.results.Result;
import br.com.bichofull.bichofull.repository.DrawRepository;
import br.com.bichofull.bichofull.repository.ResultRepository;
import br.com.bichofull.bichofull.utils.VerifyAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class ResultService {

    @Autowired
    private DrawRepository drawRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private VerifyAnimal verifyAnimal;

    private final Random random = new Random();

    public Result generateResults(BetType betType){
        Result result = new Result();

        // Main draw
        Draw mainDraw = createDraw(betType);
        result.setMainDraw(mainDraw);

        // Secondary draws
        for(int i=0; i<4; i++){
            Draw draw = createDraw(betType);
            result.getSecondaryDraws().add(draw);
        }

        resultRepository.save(result);
        return result;
    }

    private Draw createDraw(BetType betType){
        Draw draw = new Draw();
        int luckyNumber = random.nextInt(betType.getMinNumber(), betType.getMaxNumber()+1);
        Animal animal = verifyAnimal.verifyAnimal(luckyNumber, betType);
        draw.setNumber(luckyNumber);
        draw.setAnimal(animal);
        return draw;
    }
}
