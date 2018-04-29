package com.agilemonkeys.test.crm.server.resource.service;

import com.agilemonkeys.test.crm.server.resource.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerService {
    CustomerDto createOrUpdateCustomer(CustomerDto customerDto);

    void deleteCustomer(String idCustomer);

    Optional<CustomerDto> getCustomer(String idCustomer) throws EntityNotFoundCRMException;

    @Transactional(readOnly = true)
    Page<CustomerDto> getAllCustomers(Integer numPage, Integer numElementsForPage);

}
