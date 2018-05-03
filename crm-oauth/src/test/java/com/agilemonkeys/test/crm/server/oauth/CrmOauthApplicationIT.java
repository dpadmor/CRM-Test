package com.agilemonkeys.test.crm.server.oauth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CrmOauthApplicationIT {

    Logger log = LoggerFactory.getLogger(CrmOauthApplicationIT.class);

    @LocalServerPort
    protected int serverPort;

    private String username = "dani1";

    private String password = "dani3";

    private OAuth2RestTemplate restTemplate;

    private OAuth2RestOperations restTemplateOperation;
    private ResourceOwnerPasswordResourceDetails resourceDetails;


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
    }

    @Test
    public void getAccessTokenOKTest () {
        Assert.isTrue(!restTemplate.getAccessToken().toString().isEmpty(), "ERROR: Empty Access Token");
        log.info ("Access Token : " + restTemplate.getAccessToken().toString());
        log.info("Token Info " +  restTemplate.getAccessToken().getAdditionalInformation().get("organization") +  " " );
    }




}
