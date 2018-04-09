package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.domain.dto.CustomerDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceIT {

    public static final String ID = "3333";

    @Autowired
    CustomerService customerService;

    @Test
    public void saveCustomerTest () {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Juan");
        customerDto.setId(ID);
        customerDto.setSurname("PM");

        CustomerDto customerSavedDto = customerService.saveCustomer(customerDto);
        Assert.isTrue(ID.equals(customerSavedDto.getId()),"Error, Customer not save");
    }


}