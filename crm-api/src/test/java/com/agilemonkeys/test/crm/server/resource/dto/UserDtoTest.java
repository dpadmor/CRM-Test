package com.agilemonkeys.test.crm.server.resource.dto;


import com.agilemonkeys.test.crm.server.resource.model.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(JUnit4.class)
public class UserDtoTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Test
    public void setStatus () {
        UserDto userDto = new UserDto();
        log.info(userDto.toString());
//        userDto.setStatus(UserStatus.USER.name());
        log.info(userDto.toString());
  //      Assert.isTrue(UserStatus.USER.name().equals(userDto.getStatus()),"ERROR: Status not changed");
    }

}