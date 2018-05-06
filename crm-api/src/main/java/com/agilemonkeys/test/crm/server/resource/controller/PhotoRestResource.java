package com.agilemonkeys.test.crm.server.resource.controller;

import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.PhotoDto;
import com.agilemonkeys.test.crm.server.resource.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo")
@Api(value = "/photo")
public class PhotoRestResource {

    @Autowired
    CustomerService customerService;



    @GetMapping(value ="/{photoId}")
    @ApiOperation(value ="/{photoId}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String photoId) throws EntityNotFoundCRMException {
        PhotoDto photo = customerService.getPhotoByPhotoId(photoId);
        ByteArrayResource byteArrayResource = new ByteArrayResource(photo.getPhoto());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + photo.getPhotoName() + "\"").body(byteArrayResource);

    }
}
