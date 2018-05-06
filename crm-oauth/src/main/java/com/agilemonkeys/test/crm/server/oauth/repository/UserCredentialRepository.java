package com.agilemonkeys.test.crm.server.oauth.repository;

import com.agilemonkeys.test.crm.server.oauth.model.entity.UserCredential;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserCredentialRepository  extends CrudRepository<UserCredential, String> {

    @Modifying
    @Query("update UserCredential set rol = :newRol where username = :userId")
    void updateRol (@Param(value = "newRol") String rol, @Param(value = "userId") String username);
}
