package com.agilemonkeys.test.crm.server.resource.controller;

import com.agilemonkeys.test.crm.server.resource.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.VersionDto;
import com.agilemonkeys.test.crm.server.resource.util.RestTokenUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceIT {

    Logger log = LoggerFactory.getLogger(CustomerResourceIT.class);

    @LocalServerPort
    protected int serverPort;

    @Autowired
    CustomerRestResource customerResource;

    private OAuth2RestTemplate restTemplate;

    private String urlController;


    @Before
    public void setUp() throws Exception {
        restTemplate = RestTokenUtil.buildRestTemplate();
        urlController = String.format("http://localhost:%d/customers",serverPort);
    }

    private CustomerDto buildCustomerDto (String id) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        customerDto.setName("Prueba");
        customerDto.setSurname("Surname");
        return customerDto;
    }
    @Test
    public void getAllCustomerTest () {
        List<CustomerDto> responseList = restTemplate.exchange(urlController + "/all?numElements=10&numPage=0",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerDto>>() {}).getBody();

        Assert.isTrue(!responseList.isEmpty(),"ERROR findAllCustomer");
        responseList.forEach(customerDto -> log.info(customerDto.toString()));
    }

    @Test
    public void getCustomerTest () {
        ResponseEntity<CustomerDto> response = restTemplate.getForEntity(urlController + "/1", CustomerDto.class);
        Assert.isTrue("1".equals(response.getBody().getId()), "ERROR customer not found");
        log.info(response.getBody().toString());
    }

    @Test(expected = HttpClientErrorException.class)
    public void createCustomerCheckRequiredFieldFailTest () {
        CustomerDto customerDto = new CustomerDto();
        String id = "123123";
        customerDto.setId(id);
        customerDto.setName("Prueba");
        ResponseEntity<CustomerDto> responseEntity = restTemplate.postForEntity(urlController + "/", customerDto, CustomerDto.class);
        Assert.isTrue(id.equals(responseEntity.getBody().getId()), "ERROR customer not created");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void createCustomerTest () {
        String id = "123123";
        CustomerDto customerDto = buildCustomerDto(id);
        ResponseEntity<CustomerDto> responseEntity = restTemplate.postForEntity(urlController + "/", customerDto, CustomerDto.class);
        Assert.isTrue(id.equals(responseEntity.getBody().getId()), "ERROR customer not created");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void CheckAuditWithCreationTest() {
        String id = "123123";
        CustomerDto customerDto = buildCustomerDto(id);
        ResponseEntity<CustomerDto> responseEntity = restTemplate.postForEntity(urlController + "/", customerDto, CustomerDto.class);
        Assert.isTrue(responseEntity.getBody().getCreatedBy() != null
                && responseEntity.getBody().getCreatedAt() != null, "ERROR audit customer not created");
        log.info(responseEntity.getBody().toString());
    }

    @Test
    public void CheckAuditWithUpdatingTest() {
        String id = "123123";
        CustomerDto customerDto = buildCustomerDto(id);
        ResponseEntity<CustomerDto> responseEntityCreated = restTemplate.postForEntity(urlController + "/", customerDto, CustomerDto.class);
        log.info(responseEntityCreated.getBody().toString());
        customerDto.setSurname("newSurname");
        ResponseEntity<CustomerDto>  responseEntityUpdated = restTemplate.postForEntity(urlController + "/", customerDto, CustomerDto.class);
        Assert.isTrue(!responseEntityCreated.getBody().getCreatedAt().equals(
                responseEntityUpdated.getBody().getUpdatedAt())
        , "ERROR audit customer not updated");
        log.info(responseEntityUpdated.getBody().toString());
    }

    @Test
    public void getVersion () {
        ResponseEntity<VersionDto> response = restTemplate.getForEntity(urlController + "/version", VersionDto.class);
        Assert.isTrue(response.getStatusCode().is2xxSuccessful() && !response.getBody().getVersion().isEmpty(), "ERROR getVersion");
        log.info("Version " + response.getBody());
    }


}