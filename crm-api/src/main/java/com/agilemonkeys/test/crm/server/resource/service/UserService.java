package com.agilemonkeys.test.crm.server.resource.service;

import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto);

    void deleteUser(String idUser);

    Optional<UserDto> getUser(String idUser) throws EntityNotFoundCRMException;

    @Transactional(readOnly = true)
    Page<UserDto> getAllUsers(Integer numPage, Integer numElementsForPage);
}
