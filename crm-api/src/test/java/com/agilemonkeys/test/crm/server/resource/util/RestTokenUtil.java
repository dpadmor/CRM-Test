package com.agilemonkeys.test.crm.server.resource.util;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import java.util.Arrays;

public class RestTokenUtil {

    private static final String OAUTH_TOKEN_URL = "http://localhost:8081/oauth/token";
    private static final String CLIENT_AUTH_CODE = "clientauthcode";
    private static final String CLIENT_SECRET = "123456";
    private static final String PASSWORD = "password";
    private static final String SCOPE_OAUTH[] = {"read", "write"};
    private static final String username = "dani1";
    private static final String password = "dani3";

    public static OAuth2RestTemplate buildRestTemplate() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setAccessTokenUri(OAUTH_TOKEN_URL);
        resourceDetails.setClientId(CLIENT_AUTH_CODE);
        resourceDetails.setClientSecret(CLIENT_SECRET);
        resourceDetails.setGrantType(PASSWORD);
        resourceDetails.setScope(Arrays.asList(SCOPE_OAUTH));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }
}
