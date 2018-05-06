package com.agilemonkeys.test.crm.server.oauth.controller;

import com.agilemonkeys.test.crm.server.oauth.model.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestResourceIT {

    Logger log = LoggerFactory.getLogger(UserRestResourceIT.class);


    @LocalServerPort
    protected int serverPort;

    private String username = "dani1";

    private String password = "dani3";

    private ResourceOwnerPasswordResourceDetails resourceDetails;
    private OAuth2RestTemplate restTemplate;
    private String urlController;


    @Before
    public void setUp() throws Exception {
        resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setAccessTokenUri(String.format("http://localhost:%d/oauth/token", serverPort));
        resourceDetails.setClientId("clientauthcode");
        resourceDetails.setClientSecret("123456");
        resourceDetails.setGrantType("password");
        resourceDetails.setScope(Arrays.asList("read", "write"));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));

        urlController = String.format("http://localhost:%d/users",serverPort);
    }


    @Test
    public void getUser() {
        String username = "dani1";

        ResponseEntity<UserDto> response = restTemplate.getForEntity(urlController + "/" + username, UserDto.class);
        Assert.isTrue(username.equals(response.getBody().getUsername())," ERROR user not found");
        log.info(response.getBody().toString());
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void getVersion() {
    }
}