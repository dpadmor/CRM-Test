package com.agilemonkeys.test.crm.repository;

import com.agilemonkeys.test.crm.domain.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {




}
