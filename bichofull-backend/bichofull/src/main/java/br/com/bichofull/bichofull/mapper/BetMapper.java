package br.com.bichofull.bichofull.mapper;

import br.com.bichofull.bichofull.domain.bet.Bet;
import br.com.bichofull.bichofull.domain.bet.BetResultDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BetMapper {
    BetResultDTO toDTO(Bet entity);
}
