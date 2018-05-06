package com.agilemonkeys.test.crm.server.resource.repository;


import com.agilemonkeys.test.crm.server.resource.model.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRepositoryIT {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerRepository customerRepostiory;

    @Test
    public void findAllTest () {
        int size = customerRepostiory.findAll( new PageRequest(0, 10)).getSize();

        Assert.isTrue(size > 0,"No se ha obtenido ningún elemento");
    }

    @Test
    public void findByIdTest () {
        Customer customer = customerRepostiory.findOne("1");
        Assert.isTrue(customer != null, "No se ha encontrado el ID " + 1);
        log.info(customer.toString());

    }
    @Test
    public void saveFailTest () {
        Customer customer = new Customer("123", "", "Padron Morales");

        Customer customerSave = customerRepostiory.save(customer);
        Assert.isTrue(customerSave.getId() != null,"ERROR Customer is NULL");
        log.info("Save customer " + customerSave.toString());
    }
}
