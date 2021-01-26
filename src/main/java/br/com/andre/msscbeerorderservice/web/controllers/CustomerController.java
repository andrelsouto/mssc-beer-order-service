package br.com.andre.msscbeerorderservice.web.controllers;

import br.com.andre.msscbeerorderservice.services.CustomerService;
import br.com.andre.msscbeerorderservice.web.model.CustomerDto;
import br.com.andre.msscbeerorderservice.web.model.CustomerPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_PAGE_SIZE = "35";

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerPagedList> listCustomers(@RequestParam (value = "pageNumber", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {

        return ResponseEntity.ok(customerService.listCustomers(PageRequest.of(pageNumber, pageSize)));

    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable UUID customerId) {

        return ResponseEntity.ok(customerService.findCustomerById(customerId));

    }

}
