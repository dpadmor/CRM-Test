package com.agilemonkeys.test.crm.server.resource.repository;

import com.agilemonkeys.test.crm.server.resource.model.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {




}
