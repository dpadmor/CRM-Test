package com.agilemonkeys.test.crm.controller;

import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.model.dto.UserDto;
import com.agilemonkeys.test.crm.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api(value = "/users")
public class UserRestResource {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value= "/{userId}")
    @ApiOperation(value= "/{userId}")
    public ResponseEntity<UserDto> getUser (@PathVariable String userId) throws EntityNotFoundCRMException {
        return null;
    }

}
