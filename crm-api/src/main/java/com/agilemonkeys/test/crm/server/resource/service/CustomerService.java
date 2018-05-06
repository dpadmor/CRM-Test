package com.agilemonkeys.test.crm.server.resource.service;

import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.CustomerDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.PhotoDto;
import com.agilemonkeys.test.crm.server.resource.model.dto.VersionDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface CustomerService {
    CustomerDto createOrUpdateCustomer(CustomerDto customerDto);

    void deleteCustomer(String idCustomer);

    Optional<CustomerDto> getCustomer(String idCustomer) throws EntityNotFoundCRMException;

    Page<CustomerDto> getAllCustomers(Integer numPage, Integer numElementsForPage);

    String uploadPhoto(String idCustomer, MultipartFile photo) throws IOException, EntityNotFoundCRMException;

    PhotoDto getPhotoByCustomer(String customerId) throws EntityNotFoundCRMException;

    PhotoDto getPhotoByPhotoId(String photoId) throws EntityNotFoundCRMException;

    VersionDto getVersion();
}