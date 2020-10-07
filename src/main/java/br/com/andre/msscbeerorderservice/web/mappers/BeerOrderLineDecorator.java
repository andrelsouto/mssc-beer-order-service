package br.com.andre.msscbeerorderservice.web.mappers;


import br.com.andre.msscbeerorderservice.domain.BeerOrderLine;
import br.com.andre.msscbeerorderservice.services.beer.BeerService;
import br.com.andre.msscbeerorderservice.services.beer.model.BeerDto;
import br.com.andre.msscbeerorderservice.web.model.BeerOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class BeerOrderLineDecorator implements BeerOrderLineMapper {

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        Optional<BeerDto> beerDto = beerService.getBeerByUpc(line.getUpc());
        BeerOrderLineDto beerOrderLineDto = beerOrderLineMapper.beerOrderLineToDto(line);
        beerDto.ifPresent(beer -> {
            beerOrderLineDto.setBeerName(beer.getBeerName());
            beerOrderLineDto.setBeerStyle(beer.getBeerStyle());
            beerOrderLineDto.setBeerId(beer.getId());
            beerOrderLineDto.setPrice(beer.getPrice());
        });

        return beerOrderLineDto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        return beerOrderLineMapper.dtoToBeerOrderLine(dto);
    }
}
