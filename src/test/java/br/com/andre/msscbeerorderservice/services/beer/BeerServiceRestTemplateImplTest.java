package br.com.andre.msscbeerorderservice.services.beer;

import br.com.andre.msscbeerorderservice.bootstrap.BeerOrderBootStrap;
import br.com.andre.msscbeerorderservice.services.beer.model.BeerDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Disabled
@SpringBootTest
class BeerServiceRestTemplateImplTest {

    @Autowired
    private BeerService beerService;

    @Test
    void getBeerByUpc() {

        Optional<BeerDto> dto = beerService.getBeerByUpc(BeerOrderBootStrap.BEER_1_UPC);

        System.out.println(dto);
    }
}