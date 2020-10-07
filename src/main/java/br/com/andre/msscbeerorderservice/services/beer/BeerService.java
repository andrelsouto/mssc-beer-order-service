package br.com.andre.msscbeerorderservice.services.beer;

import br.com.andre.msscbeerorderservice.services.beer.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Optional<BeerDto> getBeerByUpc(String upc);
    Optional<BeerDto> getBeerById(UUID id);
}
