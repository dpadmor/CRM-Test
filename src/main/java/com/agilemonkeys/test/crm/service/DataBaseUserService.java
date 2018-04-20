package com.agilemonkeys.test.crm.service;

import com.agilemonkeys.test.crm.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.model.dto.UserDto;
import com.agilemonkeys.test.crm.model.entity.User;
import com.agilemonkeys.test.crm.model.entity.UserStatus;
import com.agilemonkeys.test.crm.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DataBaseUserService implements UserService {
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createOrUpdateUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved, UserDto.class);
    }

    @Override
    public void deleteUser(String idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public UserDto changeStatusOfUser(String idUser, UserStatus newStatus) {
        Optional<User> user = userRepository.findById(idUser);
        user.get().setStatus(newStatus.name());
        User newUser = userRepository.save(user.get());
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(newUser, UserDto.class);
        //userDto.setStatus(newStatus.name());
        return userDto;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUser(String idUser) throws EntityNotFoundCRMException {
        ModelMapper modelMapper = new ModelMapper();
        Optional<User> user = userRepository.findById(idUser);
        if (!user.isPresent()){
            throw new EntityNotFoundCRMException(idUser);
        }
        UserDto userDto = modelMapper.map(user.get(), UserDto.class);
        //userDto.setStatus(user.get().getStatus().name());
        return Optional.of(userDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getAllUsers(Integer numPage, Integer numElementsForPage) {
        PageRequest pageRequest = new PageRequest(numPage, numElementsForPage);
        Page<User> users = userRepository.findAll(pageRequest);
        return users;
    }

}
