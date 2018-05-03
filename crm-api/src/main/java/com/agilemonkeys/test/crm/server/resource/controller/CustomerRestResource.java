package com.agilemonkeys.test.crm.server.resource.controller;


import com.agilemonkeys.test.crm.server.resource.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.server.resource.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
@Api(value = "/customers")
public class CustomerRestResource {

    @Autowired
    CustomerService customerService;

    @GetMapping(value= "/all")
    @ApiOperation(value= "/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllCustomer (Integer  numElements, Integer numPage) {
        Page<CustomerDto> allCustomers = customerService.getAllCustomers(numPage, numElements);
        return new ResponseEntity<>(allCustomers.getContent(), HttpStatus.OK);
    }

    @GetMapping(value= "/{customerId}")
    @ApiOperation(value= "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer (@PathVariable String customerId) throws EntityNotFoundCRMException {
        Optional<CustomerDto> customer = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customer.get(),HttpStatus.OK);
    }

    @DeleteMapping(value="/{customerId}")
    @ApiOperation(value= "/{customerId}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable  String customerId) throws EntityNotFoundCRMException {
        Optional<CustomerDto> customer = customerService.getCustomer(customerId);
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(customer.get(),HttpStatus.OK);
    }

    @PostMapping(value="/")
    @ApiOperation(value= "/")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto newCustomer = customerService.createOrUpdateCustomer(customerDto);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping(value="/version")
    @ApiOperation(value="/version")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getVersion () {
        return "1.0";
    }


}
