package com.agilemonkeys.test.crm.server.oauth;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncodeTest {

    Logger log = LoggerFactory.getLogger(PasswordEncodeTest.class);


    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void encodePasswordTest() {
        String passwordPlain = "12345";

        String passwordEncode1 = passwordEncoder.encode(passwordPlain);
        String passwordEncode2 = passwordEncoder.encode(passwordPlain);

        log.info("PASS 1 " + passwordEncode1);
        log.info("PASS 2 " + passwordEncode2);

        Assert.isTrue(!passwordEncode1.equals(passwordEncode2), "ERROR not salt in password");

    }
}
