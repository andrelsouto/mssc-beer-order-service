package br.com.andre.msscbeerorderservice.web.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateOrderResult {

    private UUID beerOrderId;
    private Boolean isValid;

}
