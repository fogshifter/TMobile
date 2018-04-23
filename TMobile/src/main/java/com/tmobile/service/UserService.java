package com.tmobile.service;

import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmobile.dao.UserDAO;


import com.tmobile.entity.User;

@Service
public class UserService {

	private UserDAO userDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserService(UserDAO customerDAO, ModelMapper mapper) {
		this.userDAO = customerDAO;
		this.modelMapper = mapper;
	}

	@Transactional
	public int getCustomerId(String email) {
		return userDAO.findByEmail(email).getId();
	}
	
	@Transactional
	public void insert(User user) {
		userDAO.insert(user);
	}

	@Transactional
	public Boolean isEmailAvailable(String email) {
		return (userDAO.findByEmail(email) == null);
	}
}
