package com.agilemonkeys.test.crm.controller;

import com.agilemonkeys.test.crm.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/customers")
@Api(value = "/customers")
public class CustomerRestResource {

    @Autowired
    CustomerService customerService;

    @GetMapping(value= "/{customerId}")
    @ApiOperation(value= "/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer (@PathVariable  String customerId) throws EntityNotFoundCRMException {
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


}
