package br.com.andre.msscbeerorderservice.web.model.events;

import br.com.andre.msscbeerorderservice.web.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllocateOrderRequest {

    private BeerOrderDto beerOrderDto;

}
