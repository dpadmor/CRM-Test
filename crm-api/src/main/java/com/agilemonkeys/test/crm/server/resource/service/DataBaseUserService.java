package com.agilemonkeys.test.crm.server.resource.service;


import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.resource.model.dto.UserDto;
import com.agilemonkeys.test.crm.server.resource.model.entity.User;
import com.agilemonkeys.test.crm.server.resource.model.entity.UserStatus;
import com.agilemonkeys.test.crm.server.resource.repository.UserRepository;
import com.agilemonkeys.test.crm.server.resource.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DataBaseUserService  implements UserService {

    @Autowired
    ModelMapperUtil modelMapper;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDto createUser(UserDto userDto) {
        if (userRepository.exists(userDto.getUsername())) {
            // TODO return userExistException
        }
        User user = modelMapper.map(userDto, User.class);

        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved, UserDto.class);
    }


    public UserDto updateUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved, UserDto.class);
    }


    public void deleteUser(String idUser) {
        userRepository.delete(idUser);
    }


    public UserDto changeStatusOfUser(String idUser, UserStatus newStatus) {
        User user = userRepository.findOne(idUser);
        user.setStatus(newStatus.name());
        User newUser = userRepository.save(user);
        UserDto userDto = modelMapper.map(newUser, UserDto.class);
        return userDto;
    }



    @Transactional(readOnly = true)
    public Optional<UserDto> getUser(String idUser) throws EntityNotFoundCRMException {
        User user = userRepository.findOne(idUser);
        if (user == null) {
            throw new EntityNotFoundCRMException(idUser);
        }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        //userDto.setStatus(user.get().getStatus().name());
        return Optional.of(userDto);
    }


    @Transactional(readOnly = true)
    public Page<UserDto> getAllUsers(Integer numPage, Integer numElementsForPage) {
        PageRequest pageRequest = new PageRequest (numPage, numElementsForPage);
        Page<User> users = userRepository.findAll(pageRequest);
        return modelMapper.map(users, UserDto.class);
    }

}
