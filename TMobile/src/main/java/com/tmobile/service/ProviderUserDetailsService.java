package com.tmobile.service;

import javax.transaction.Transactional;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tmobile.auth.ProviderUserDetails;
import com.tmobile.dao.UserDAO;
import com.tmobile.entity.User;

@Service
public class ProviderUserDetailsService implements UserDetailsService {
	
//	private CustomerDAO customerDAO;
//	private ManagerDAO managerDAO;
	private UserDAO userDAO;
	
	@Autowired
//	public ProviderUserDetailsService(CustomerDAO customerDAO, ManagerDAO managerDAO) {
	public ProviderUserDetailsService(UserDAO userDAO) {
		super();
//		this.customerDAO = customerDAO;
//		this.managerDAO = managerDAO;
		this.userDAO = userDAO;
	}

	@Override
	@Transactional//(readonly = true)
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
		/*Customer customer = customerDAO.findByEmail(email);
		Manager manager = managerDAO.findByEmail(email);*/
		
		User user = userDAO.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		
//		return new CustomerDetails(customer);
		return new ProviderUserDetails(user);
	}
}	
