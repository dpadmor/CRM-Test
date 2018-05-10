package com.agilemonkeys.test.crm.controller;

import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.model.dto.UserDto;
import com.agilemonkeys.test.crm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@Api(value = "/users")
public class UserRestResource {

    @Autowired
    UserService userService;

    @GetMapping(value= "/{userId}")
    @ApiOperation(value= "/{userId}")
    public ResponseEntity<UserDto> getUser (@PathVariable String userId) throws EntityNotFoundCRMException {
        Optional<UserDto> user = userService.getUser(userId);
        return new ResponseEntity<> (user.get(), HttpStatus.OK);
    }

    @GetMapping(value= "/login")
    @ApiOperation(value= "/login")
    public void loginUser (@PathVariable String user, @PathVariable String password) {
        userService.loginUser(user,password);
    }

}
