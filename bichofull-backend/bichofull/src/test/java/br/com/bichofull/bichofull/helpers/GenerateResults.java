package br.com.bichofull.bichofull.helpers;

import br.com.bichofull.bichofull.domain.results.Draw;
import br.com.bichofull.bichofull.domain.results.Result;

import java.util.ArrayList;
import java.util.List;

public class GenerateResults {

    public static Result generateResult(){
        Draw mainDraw = new Draw();
        mainDraw.setNumber(1);

        List<Draw> secondaryDraws = new ArrayList<>();
        for(int i=2; i<=5; i++){
            Draw draw = new Draw();
            draw.setNumber(i);
            secondaryDraws.add(draw);
        }

        Result result = new Result();
        result.setMainDraw(mainDraw);
        result.setSecondaryDraws(secondaryDraws);

        return result;
    }
}
