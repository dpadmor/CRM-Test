# CRM-Test
API Test - The CRM service

**Requirements**
-

- Maven 3.3.0 or above
- JDK 1.8 or above

**Compilation**
-

`mvn clean install ` in parent project crm-api



**Execution**
-

`java -jar crm-test/crm-oauth/crm-oauth-1.0.jar` for Server OAUTH2

`java -jar crm-test/crm-api/crm-api-1.0.jar` for CRM Api

**Architecture**
- 
CRM-Test contain two modules:

- Api for Customer (crm-api)
Documentations of CRUD ( http://localhost:8080/swagger-ui.html )

- Server OAUTH2 (crm-oauth)
Documentations of CRUD ( http://localhost:8081/swagger-ui.html )

![](http://sivatechlab.com/wp-content/uploads/2017/05/overall-password-flow-2.png)

1. POST  `/oauth/token` with Username and Password of the user and Client credential in the server OAUTH for Token Access

```resourceDetails = new ResourceOwnerPasswordResourceDetails();
         resourceDetails.setUsername(username);
         resourceDetails.setPassword(password);
         resourceDetails.setAccessTokenUri(String.format("http://localhost:%d/oauth/token", serverPort));
         resourceDetails.setClientId(clientauthcode);
         resourceDetails.setClientSecret(clientSecret);
         resourceDetails.setGrantType("password");
         resourceDetails.setScope(Arrays.asList("read", "write"));
         DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
         restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
         restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
```        


2. GET to API with AcceToken in Header

```
ResponseEntity<VersionDto> response = restTemplate.getForEntity(urlController + "/version", VersionDto.class);
```


