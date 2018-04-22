package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.model.dto.UserDto;
import com.agilemonkeys.test.crm.model.entity.UserStatus;
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
    public void createOrUpdateUser() throws EntityNotFoundCRMException {
        UserDto userDto = new UserDto("Pepe1980", "123456", "Pepe", "PM");
        UserDto newUser = userService.createOrUpdateUser(userDto);

        Optional<UserDto> user = userService.getUser(newUser.getUsername());
        Assert.isTrue(user.isPresent());
    }

    @Test(expected = EntityNotFoundCRMException.class)
    public void deleteUser() throws EntityNotFoundCRMException {
        Optional<UserDto> user = userService.getUser("44");
        Assert.isTrue(user.isPresent());

        userService.deleteUser(user.get().getUsername());
        userService.getUser(user.get().getUsername());
    }

    @Test(expected = EntityNotFoundCRMException.class)
    public void getUserFail() throws EntityNotFoundCRMException {
        userService.getUser("88");
    }

    @Test
    public void getUser() throws EntityNotFoundCRMException {
        Optional<UserDto> user = userService.getUser("22");
        Assert.isTrue(user.isPresent());
    }

    @Test
    public void getAllUsers() {
        Page<UserDto> allUsers = userService.getAllUsers(0, 10);
        allUsers.forEach(user ->
                log.info(user.toString()));
        Assert.isTrue(allUsers.getTotalElements() > 0);
    }

    @Test
    public void changeStatus () throws EntityNotFoundCRMException {
        Optional<UserDto> user = userService.getUser("22");
        log.info(user.get().toString());
        Assert.isTrue(user.get().getStatus().equals(UserStatus.USER.name()));
        UserDto userDto = userService.changeStatusOfUser("22", UserStatus.ADMIN);
        Assert.isTrue(userDto.getStatus().equals(UserStatus.ADMIN.name()));
        log.info(userDto.toString());

    }
}