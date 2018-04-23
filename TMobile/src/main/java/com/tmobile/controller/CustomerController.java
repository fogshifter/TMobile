package com.tmobile.controller;

import com.tmobile.auth.ProviderUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmobile.dto.ProfileDTO;
import com.tmobile.service.UserService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private UserService userService;
	
	@Autowired
	public CustomerController(UserService service) {
		this.userService = service;
	}
	
	@GetMapping("/{customerId}")
//	public ModelAndView getProfile(@PathVariable int customerId) {
    public ModelAndView getProfile() {

        ProviderUserDetails userDetails = (ProviderUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
//		ProfileDTO profile = userService.getCustomerProfile(customerId);
        ProfileDTO profile = userService.getCustomerProfile(userDetails.getUserId());
		
		ModelAndView view = new ModelAndView("profile");		
		view.addObject("profile", profile);
		return view;
	}
}
