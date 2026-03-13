package br.com.bichofull.bichofull.domain.results;

import java.util.List;

public record ResultDTO(
        DrawDTO mainDraw,
        List<DrawDTO> secondaryDraws
) {

    public ResultDTO(Result result){
        this(
                new DrawDTO(result.getMainDraw()),
                result.getSecondaryDraws().stream().map(DrawDTO::new).toList()
        );
    }
}
