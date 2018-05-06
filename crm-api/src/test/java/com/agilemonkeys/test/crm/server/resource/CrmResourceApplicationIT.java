package com.agilemonkeys.test.crm.server.resource;

import com.agilemonkeys.test.crm.server.resource.util.RestTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrmResourceApplicationIT {

    Logger log = LoggerFactory.getLogger(CrmResourceApplicationIT.class);

    @LocalServerPort
    protected int serverPort;


    private OAuth2RestTemplate restTemplate;


    @Before
    public void setUp() throws Exception {
        restTemplate = RestTokenUtil.buildRestTemplate();
    }

    @Test
    public void getAccessTokenOKTest () {
        Assert.isTrue(!restTemplate.getAccessToken().toString().isEmpty(), "ERROR: Empty Access Token");
        log.info ("Access Token : " + restTemplate.getAccessToken().toString());
        log.info("Token Info " +  restTemplate.getAccessToken().getAdditionalInformation().get("organization") +  " " );

    }

    @Test
    public void getVersion () {
        ResponseEntity<String> response = restTemplate.getForEntity(String.format("http://localhost:%d/users/version", serverPort), String.class);
        log.info(response.getBody());
    }


}
