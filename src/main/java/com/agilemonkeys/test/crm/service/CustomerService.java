package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.domain.dto.CustomerDto;

public interface CustomerService {
    CustomerDto saveCustomer(CustomerDto customerDto);
}
