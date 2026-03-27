package br.com.bichofull.bichofull.dtos;

import br.com.bichofull.bichofull.domain.bet.BetResultDTO;

import java.util.List;

public record HistoryBetDTO(
        List<BetResultDTO> bets,
        long totalElements,
        int totalPages
) {
}
