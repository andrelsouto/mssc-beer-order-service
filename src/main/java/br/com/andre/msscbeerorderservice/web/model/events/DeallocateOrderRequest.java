package br.com.andre.msscbeerorderservice.web.model.events;

import br.com.andre.msscbeerorderservice.web.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeallocateOrderRequest {

    BeerOrderDto beerOrderDto;

}
