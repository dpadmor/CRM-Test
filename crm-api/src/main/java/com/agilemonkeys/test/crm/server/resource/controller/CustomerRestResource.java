package com.agilemonkeys.test.crm.server.resource.controller;


import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.PhotoDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.VersionDto;
import com.agilemonkeys.test.crm.server.resource.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/customers")
@Api(value = "/customers")
public class CustomerRestResource {

    @Autowired
    CustomerService customerService;

    @GetMapping(value= "/all")
    @ApiOperation(value= "/all")
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

    @PostMapping(value ="{customerId}/photo")
    @ApiOperation(value ="{customerId}/photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable String customerId, @RequestBody MultipartFile photoCustomer) throws IOException, EntityNotFoundCRMException {
        String idPhoto = customerService.uploadPhoto(customerId, photoCustomer);
        return new ResponseEntity<>("Photo uploaded with ID " + idPhoto, HttpStatus.OK);
    }

    @GetMapping(value ="{customerId}/photo")
    @ApiOperation(value ="{customerId}/photo")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String customerId) throws EntityNotFoundCRMException {
        PhotoDto photo = customerService.getPhotoByCustomer(customerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(photo.getPhoto(), headers, HttpStatus.OK);
    }


    @GetMapping(value="/version")
    @ApiOperation(value="/version")
    public ResponseEntity<VersionDto> getVersion () {
        return new ResponseEntity<>(customerService.getVersion(), HttpStatus.OK);
    }


}
