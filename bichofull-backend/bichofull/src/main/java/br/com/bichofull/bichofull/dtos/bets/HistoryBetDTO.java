package br.com.bichofull.bichofull.dtos.bets;

import java.util.List;

public record HistoryBetDTO(
        List<BetResultDTO> bets,
        long totalElements,
        int totalPages
) {
}
