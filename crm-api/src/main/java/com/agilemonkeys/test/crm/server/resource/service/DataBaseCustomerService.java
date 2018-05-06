package com.agilemonkeys.test.crm.server.resource.service;

import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.PhotoDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.VersionDto;
import com.agilemonkeys.test.crm.server.resource.model.entity.Customer;
import com.agilemonkeys.test.crm.server.resource.repository.CustomerRepository;
import com.agilemonkeys.test.crm.server.resource.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

@Service
public class DataBaseCustomerService  implements CustomerService  {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapperUtil modelMapper;

    @Override
    public CustomerDto createOrUpdateCustomer(CustomerDto customerDto) {
        Customer customerSaved;
        Customer customer = customerRepository.findOne(customerDto.getId());
        if (customer != null && customer.getId() != null) {
            customer.setName(customerDto.getName());
            customer.setSurname(customerDto.getSurname());
            customerSaved =customerRepository.save(customer);
        } else {
            customer = modelMapper.map(customerDto,Customer.class);
            customerSaved =customerRepository.save(customer);

        }
        return modelMapper.map(customerSaved, CustomerDto.class);
    }

    @Override
    public void deleteCustomer (String idCustomer) {
        customerRepository.delete(idCustomer);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDto> getCustomer(String idCustomer) throws EntityNotFoundCRMException {
        Customer customer = customerRepository.findOne(idCustomer);
        if (customer == null) {
            throw new EntityNotFoundCRMException(idCustomer);
        }
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        return Optional.of(customerDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDto> getAllCustomers(Integer numPage, Integer numElementsForPage) {
        Assert.notNull(numPage,"ERROR parameter 'numPage' is NULL");
        Assert.notNull(numElementsForPage,"ERROR parameter 'numElementsForPage' is NULL");

        PageRequest pageRequest =  new PageRequest(numPage, numElementsForPage);
        Page<Customer> customers = customerRepository.findAll(pageRequest);
        return modelMapper.map(customers,CustomerDto.class);
    }


    @Override
    public String uploadPhoto (String idCustomer, @RequestParam MultipartFile photo) throws IOException, EntityNotFoundCRMException {
        Assert.notNull(photo, "ERROR photo is NULL");
        Assert.notNull(photo.getBytes(), "ERROR photo is NULL");

        Customer customer = customerRepository.findOne(idCustomer);

        if (customer == null) {
            throw new EntityNotFoundCRMException(idCustomer);
        }

        customer.setPhoto(photo.getBytes());
        customer.setIdphoto(UUID.randomUUID().toString());
        customer.setPhotoName(photo.getOriginalFilename());

        customerRepository.save(customer);
        return customer.getIdphoto();
    }

    @Override
    public PhotoDto getPhotoByCustomer(String customerId) throws EntityNotFoundCRMException {
        Customer customer = customerRepository.findOne(customerId);

        if (customer == null) {
            throw new EntityNotFoundCRMException(customerId);
        }

        return modelMapper.map(customer, PhotoDto.class);
    }

    @Override
    public PhotoDto getPhotoByPhotoId(String photoId) throws EntityNotFoundCRMException {
        Customer customer = customerRepository.findByPhotoId(photoId);

        if (customer == null) {
            throw new EntityNotFoundCRMException(photoId);
        }

        return modelMapper.map(customer, PhotoDto.class);
    }

    @Override
    public VersionDto getVersion() {
        ResourceBundle rb = ResourceBundle.getBundle("mavenproject");
        return new VersionDto(rb.getString("version.cliente"));
    }


}
