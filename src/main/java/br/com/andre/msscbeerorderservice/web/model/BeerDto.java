package br.com.andre.msscbeerorderservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id = null;
    private Integer version = null;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate = null;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate = null;
    private String beerName;
    private String beerStyle;
    private String upc;
    private Integer quantityOnHand;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;
}
