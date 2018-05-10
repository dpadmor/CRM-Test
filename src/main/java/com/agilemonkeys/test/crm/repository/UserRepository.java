package com.agilemonkeys.test.crm.repository;

import com.agilemonkeys.test.crm.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
