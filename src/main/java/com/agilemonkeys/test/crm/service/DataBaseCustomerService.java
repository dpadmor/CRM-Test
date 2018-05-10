package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.model.entity.Customer;
import com.agilemonkeys.test.crm.repository.CustomerRepository;
import com.agilemonkeys.test.crm.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DataBaseCustomerService  implements CustomerService  {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapperUtil modelMapper;

    @Override
    public CustomerDto createOrUpdateCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Customer customerSaved = customerRepository.save(customer);
        return modelMapper.map(customerSaved, CustomerDto.class);
    }

    @Override
    public void deleteCustomer (String idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDto> getCustomer(String idCustomer) throws EntityNotFoundCRMException {
        Optional<Customer> customer = customerRepository.findById(idCustomer);
        if (!customer.isPresent()){
            throw new EntityNotFoundCRMException(idCustomer);
        }
        CustomerDto customerDto = modelMapper.map(customer.get(), CustomerDto.class);
        return Optional.of(customerDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDto> getAllCustomers(Integer numPage, Integer numElementsForPage) {
        PageRequest pageRequest = new PageRequest(numPage, numElementsForPage);
        Page<Customer> customers = customerRepository.findAll(pageRequest);
        return modelMapper.map(customers,CustomerDto.class);
    }



}
