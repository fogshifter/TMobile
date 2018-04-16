package com.tmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmobile.dao.UserDAO;
import com.tmobile.entity.User;
import com.tmobile.service.CustomerService;

@Controller
@RequestMapping("/")
public class IndexController {
	
//	@Autowired
//	private CustomerService s;
//	private UserDAO s;
	
//	@Autowired PasswordEncoder encoder;
	
	@GetMapping
	public String indexPage() {
		
		/*User user = new User();
		user.setAddress("Sun");
		user.setEmail("supergod@gmail.com");
		user.setPassword(encoder.encode("supergod"));
		user.setFirstName("Super");
		user.setLastName("God");
		user.setRole("ROLE_MANAGER");

		s.insert(user);*/
		
		return "index";
	}
}
