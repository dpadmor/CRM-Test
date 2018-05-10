package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.model.dto.UserDto;
import com.agilemonkeys.test.crm.model.entity.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String idUser);

    Optional<UserDto> getUser (String idUser) throws EntityNotFoundCRMException;

    @Transactional(readOnly = true)
    Page<UserDto> getAllUsers(Integer numPage, Integer numElementsForPage);

    UserDto changeStatusOfUser (String idUser, UserStatus newStatus);

    void loginUser(String user, String password);
}
