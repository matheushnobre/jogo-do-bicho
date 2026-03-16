package br.com.bichofull.bichofull.domain.results;

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
