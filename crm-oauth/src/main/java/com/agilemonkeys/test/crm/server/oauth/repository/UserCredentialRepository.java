package com.agilemonkeys.test.crm.server.oauth.repository;

import com.agilemonkeys.test.crm.server.oauth.model.entity.UserCredential;
import org.springframework.data.repository.CrudRepository;

public interface UserCredentialRepository  extends CrudRepository<UserCredential, String> {
}
