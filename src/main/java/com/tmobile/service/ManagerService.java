package com.tmobile.service;

//import org.springframework.a
//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.lang.reflect.Type;
import java.util.List;

import com.tmobile.dao.UserDAO;
import com.tmobile.dto.CustomerDTO;
import com.tmobile.entity.User;

@Service
public class ManagerService {

/*	private UserDAO customerDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	ManagerService(UserDAO customerDao, ModelMapper mapper) {
		this.customerDAO = customerDao;
		this.modelMapper = mapper;
	}*/
	
	/*@Transactional
	public List<CustomerDTO> getCustomersList() {
		List<User> customers = customerDAO.getAll();
		
		Type taretListType = new TypeToken<List<CustomerDTO>>() {}.getType();
		return modelMapper.map(customers, taretListType);
	}*/
}
