package com.agilemonkeys.test.crm.server.resource.service;


import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIT {

    Logger log = LoggerFactory.getLogger("UserServiceIT");


    @Autowired
    UserService userService;

    @Test
    public void createUserTest() throws EntityNotFoundCRMException {
        UserDto userDto = new UserDto("Pepe1980",  "Pepe", "PM");
        UserDto newUser = userService.createUser(userDto);

        Optional<UserDto> user = userService.getUser(newUser.getUsername());
        Assert.isTrue(user.isPresent(),"ERROR: User not created");
        log.info(user.toString());
    }

    @Test(expected = EntityNotFoundCRMException.class)
    public void deleteUserTest() throws EntityNotFoundCRMException {
        Optional<UserDto> user = userService.getUser("44");
        Assert.isTrue(user.isPresent());

        userService.deleteUser(user.get().getUsername());
        userService.getUser(user.get().getUsername());
    }

    @Test(expected = EntityNotFoundCRMException.class)
    public void getUserFailTest() throws EntityNotFoundCRMException {
        userService.getUser("88");
    }

    @Test
    public void getUserTest() throws EntityNotFoundCRMException {
        Optional<UserDto> user = userService.getUser("22");
        Assert.isTrue(user.isPresent());
    }

    @Test
    public void getAllUsersTest() {
        Page<UserDto> allUsers = userService.getAllUsers(0, 10);
        allUsers.forEach(user ->
                log.info(user.toString()));
        Assert.isTrue(allUsers.getTotalElements() > 0);
    }


}