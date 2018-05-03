package com.agilemonkeys.test.crm.server.resource.repository;

import com.agilemonkeys.test.crm.server.resource.model.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
