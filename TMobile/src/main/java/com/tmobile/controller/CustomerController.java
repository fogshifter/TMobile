package com.tmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmobile.dto.ProfileDTO;
import com.tmobile.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService service) {
		this.customerService = service;
	}
	
	@GetMapping("/{customerId}")
	public ModelAndView getProfile(@PathVariable int customerId) {
	
		ProfileDTO profile = customerService.getUserProfile(customerId);
		
		ModelAndView view = new ModelAndView("profile");		
		view.addObject("profile", profile);
		return view;
	}
}
