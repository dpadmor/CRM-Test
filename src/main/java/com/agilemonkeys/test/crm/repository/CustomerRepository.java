package com.agilemonkeys.test.crm.repository;

import com.agilemonkeys.test.crm.domain.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;


public interface CustomerRepository extends Repository<Customer, String> {

    Page<Customer>  findAll (Pageable pageable);
}
