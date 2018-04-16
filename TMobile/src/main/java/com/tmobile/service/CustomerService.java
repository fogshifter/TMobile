package com.tmobile.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmobile.dao.UserDAO;
import com.tmobile.dto.ProfileDTO;
import com.tmobile.entity.User;

@Service
public class CustomerService {

	private UserDAO userDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	public CustomerService(UserDAO customerDAO, ModelMapper mapper) {
		this.userDAO = customerDAO;
		this.modelMapper = mapper;
	}
	
	@Transactional//(readonly = true)
	public ProfileDTO getUserProfile(int customerId) {
//		ProfileDTO profile = new;
		User customer = userDAO.findById(customerId);
		
//		modelMapper.
		
		ProfileDTO profile = modelMapper.map(userDAO, ProfileDTO.class);
		return profile;
	}
	
	@Transactional
	public int getCustomerId(String email) {
		return userDAO.findByEmail(email).getId();
	}
	
	@Transactional
	public void insert(User user) {
		userDAO.insert(user);
	}
}
