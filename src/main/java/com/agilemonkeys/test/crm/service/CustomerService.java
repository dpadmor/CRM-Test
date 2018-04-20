package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.model.entity.Customer;
import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CustomerService {
    CustomerDto createOrUpdateCustomer(CustomerDto customerDto);

    void deleteCustomer(String idCustomer);

    Optional<CustomerDto> getCustomer (String idCustomer) throws EntityNotFoundCRMException;

    @Transactional(readOnly = true)
    Page<Customer> getAllCustomers(Integer numPage, Integer numElementsForPage);

}
