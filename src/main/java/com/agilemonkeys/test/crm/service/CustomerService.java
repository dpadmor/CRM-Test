package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.domain.dto.CustomerDto;
import com.agilemonkeys.test.crm.domain.entity.Customer;
import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerService {
    CustomerDto updateCustomer(CustomerDto customerDto);

    Optional<CustomerDto> getCustomer (String idCustomer) throws EntityNotFoundCRMException;

    @Transactional(readOnly = true)
    Page<Customer> getAllCustomer(Integer numPage, Integer numElementsForPage);
}
