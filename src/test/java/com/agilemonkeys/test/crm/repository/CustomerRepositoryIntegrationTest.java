package com.agilemonkeys.test.crm.repository;

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
public class CustomerRepositoryIntegrationTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomerRepository customerRepostiory;

    @Test
    public void executeQuery () {
        int size = customerRepostiory.findAll(PageRequest.of(0, 10)).getSize();

        Assert.isTrue(size > 0,"Error");
    }
}
