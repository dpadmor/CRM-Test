package com.agilemonkeys.test.crm.server.oauth.controller;


import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.oauth.model.dto.UserDto;
import com.agilemonkeys.test.crm.server.oauth.service.UserCredentialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
@Api(value = "/users")
public class UserRestResource {

    @Autowired
    UserCredentialService userService;


    @GetMapping(value= "/{userId}")
    @ApiOperation(value= "/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> getUser (@PathVariable String userId) throws EntityNotFoundCRMException {
        UserDto user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping(value="/{userId}")
    @ApiOperation(value= "/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> deleteUser(@PathVariable  String userId) throws EntityNotFoundCRMException {
        UserDto user = userService.getUser(userId);
        userService.deleteUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping(value="/")
    @ApiOperation(value= "/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(@Valid UserDto userDto) {
        UserDto newUser = userService.createOrUpdateUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }




    @GetMapping(value="/version")
    @ApiOperation(value="/version")
    public String getVersion () {  return userService.getVersion();
    }


}
