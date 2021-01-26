package br.com.andre.msscbeerorderservice.services;

import br.com.andre.msscbeerorderservice.domain.Customer;
import br.com.andre.msscbeerorderservice.repositories.CustomerRepository;
import br.com.andre.msscbeerorderservice.web.mappers.CustomerMapper;
import br.com.andre.msscbeerorderservice.web.model.CustomerDto;
import br.com.andre.msscbeerorderservice.web.model.CustomerPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerPagedList listCustomers(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        return new CustomerPagedList(customerPage
        .stream()
        .map(customerMapper::customerToCustomerDto)
        .collect(Collectors.toList()), PageRequest.of(
                customerPage.getPageable().getPageNumber(),
                customerPage.getPageable().getPageSize()),
                customerPage.getTotalElements()
        );
    }

    @Override
    public CustomerDto findCustomerById(UUID id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) return customerMapper.customerToCustomerDto(customerOptional.get());

        return null;
    }
}
