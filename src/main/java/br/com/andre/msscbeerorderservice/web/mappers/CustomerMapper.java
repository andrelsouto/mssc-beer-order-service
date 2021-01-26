package br.com.andre.msscbeerorderservice.web.mappers;

import br.com.andre.msscbeerorderservice.domain.Customer;
import br.com.andre.msscbeerorderservice.web.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { DateMapper.class })
public interface CustomerMapper {

    @Mapping(source = "customerName", target = "name")
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(source = "name", target = "customerName")
    Customer customerDtoToCustomer(CustomerDto customerDto);

}
