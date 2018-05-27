package com.tmobile.service;

import javax.transaction.Transactional;

import com.tmobile.auth.TMobileUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tmobile.dao.UserDAO;
import com.tmobile.entity.User;

@Service
public class ProviderUserDetailsService implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired

    public ProviderUserDetailsService(UserDAO userDAO) {
        super();
//		this.customerDAO = customerDAO;
//		this.managerDAO = managerDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userDAO.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new TMobileUserDetails(user);
    }
}	
