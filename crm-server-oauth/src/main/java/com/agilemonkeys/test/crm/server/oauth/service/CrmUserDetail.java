package com.agilemonkeys.test.crm.server.oauth.service;

import com.agilemonkeys.test.crm.server.oauth.model.CrmUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CrmUserDetail implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CrmUser userDetails = new CrmUser();
        userDetails.setUsername("Pepe");
        userDetails.setPassword("Pepe");
        return userDetails;
    }
}
