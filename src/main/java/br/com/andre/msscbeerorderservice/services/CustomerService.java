package br.com.andre.msscbeerorderservice.services;

import br.com.andre.msscbeerorderservice.web.model.CustomerDto;
import br.com.andre.msscbeerorderservice.web.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);

    CustomerDto findCustomerById(UUID id);

}
