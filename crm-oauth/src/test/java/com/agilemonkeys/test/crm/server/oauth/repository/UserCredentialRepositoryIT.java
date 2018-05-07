package com.agilemonkeys.test.crm.server.oauth.repository;

import com.agilemonkeys.test.crm.server.oauth.model.entity.UserCredential;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCredentialRepositoryIT {

    Logger log = LoggerFactory.getLogger(UserCredentialRepositoryIT.class);

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void findAllTest () {
        Iterable<UserCredential> userCredentials = userCredentialRepository.findAll();

        userCredentials.forEach(userCredential -> log.info(userCredential.toString()));
    }

    @Test
    public void insertUserTest () {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername("dani3");
        userCredential.setPassword(passwordEncoder.encode("dani3"));
        userCredential.setRol("USER");
        userCredentialRepository.save(userCredential);

        Iterable<UserCredential> userCredentials = userCredentialRepository.findAll();
        userCredentials.forEach( userCredential1 -> log.info(userCredential1.toString()));
    }

}