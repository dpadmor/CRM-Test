package com.agilemonkeys.test.crm.server.oauth.service;

import com.agilemonkeys.test.crm.commons.exception.EntityNotFoundCRMException;
import com.agilemonkeys.test.crm.server.oauth.model.dto.UserDto;
import com.agilemonkeys.test.crm.server.oauth.model.entity.UserCredential;
import com.agilemonkeys.test.crm.server.oauth.repository.UserCredentialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ResourceBundle;

@Service
public class UserCredentialService implements UserDetailsService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialRepository.findOne(username);
    }

    public UserCredential loadUser (String username) {
        return userCredentialRepository.findOne(username);
    }


    @Transactional(readOnly = true)
    public UserDto getUser(String userId) throws EntityNotFoundCRMException {
        UserCredential userCredential = userCredentialRepository.findOne(userId);
        if (userCredential == null) {
            throw new EntityNotFoundCRMException(userId);
        }
        return modelMapper.map(userCredential, UserDto.class);
    }

    public void deleteUser(String userId) throws EntityNotFoundCRMException {
        UserCredential userCredential = userCredentialRepository.findOne(userId);
        if (userCredential == null) {
            throw new EntityNotFoundCRMException(userId);
        }
        userCredentialRepository.delete(userCredential);

    }

    public UserDto createOrUpdateUser(UserDto userDto) {
        UserCredential userCredential = modelMapper.map(userDto, UserCredential.class);
        UserCredential newUserCredential = userCredentialRepository.save(userCredential);
        return modelMapper.map(newUserCredential, UserDto.class);
    }

    public String getVersion() {
        ResourceBundle rb = ResourceBundle.getBundle("mavenproject");
        return rb.getString("version.cliente");
    }
}
