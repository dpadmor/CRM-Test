package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.domain.dto.CustomerDto;
import com.agilemonkeys.test.crm.domain.entity.Customer;
import com.agilemonkeys.test.crm.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service()
public class DataBaseCustomerService implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public @NotNull CustomerDto saveCustomer(CustomerDto customerDto) {
        ModelMapper modelMapper = new ModelMapper();
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Customer customerSaved = customerRepository.save(customer);
        return modelMapper.map(customerSaved, CustomerDto.class);
    }

}
