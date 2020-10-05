package br.com.andre.msscbeerorderservice.web.mappers;

import br.com.andre.msscbeerorderservice.domain.BeerOrderLine;
import br.com.andre.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
