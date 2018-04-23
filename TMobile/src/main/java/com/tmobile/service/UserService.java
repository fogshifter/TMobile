package com.tmobile.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmobile.dao.UserDAO;
import com.tmobile.dto.CustomersListEntryDTO;
import com.tmobile.dto.ProfileDTO;

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
	
	@Transactional//(readonly = true)
	public ProfileDTO getCustomerProfile(int customerId) {
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
	
	@Transactional
//	public List<User> getAllCustomers
	public List<CustomersListEntryDTO> getCustomersList() {
		List<User> customers = userDAO.getAllCustomers();
		
		Type targetListType = new TypeToken<List<CustomersListEntryDTO>>() {}.getType();
		return modelMapper.map(customers, targetListType);
	}

	@Transactional
	public Boolean isEmailAvailable(String email) {
		return (userDAO.findByEmail(email) == null);
	}
}
