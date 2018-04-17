package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.domain.dto.CustomerDto;
import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;

import java.util.Optional;

public interface CustomerService {
    CustomerDto updateCustomer(CustomerDto customerDto);

    Optional<CustomerDto> getCustomer (String idCustomer) throws EntityNotFoundCRMException;
}
