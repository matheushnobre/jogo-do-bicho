package br.com.bichofull.bichofull.dtos.bets;

import br.com.bichofull.bichofull.domain.results.Draw;
import br.com.bichofull.bichofull.domain.results.Result;

import java.util.List;

public record ResultDTO(
        Draw mainDraw,
        List<Draw> secondaryDraws
) {

    public ResultDTO(Result result){
        this(
                result.getMainDraw(),
                result.getSecondaryDraws()
        );
    }
}
