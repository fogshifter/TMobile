package com.tmobile.controller;

import com.tmobile.auth.ProviderUserDetails;
import com.tmobile.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
//	private UserService userService;
	private ContractService contractService;
	
	@Autowired
	public CustomerController(ContractService contractService) {
	    this.contractService = contractService;
	}
	
	@GetMapping//("/{customerId}")
//	public ModelAndView getProfile(@PathVariable int customerId) {
    public ModelAndView getProfile() {

        ProviderUserDetails userDetails = (ProviderUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
//		ProfileDTO profile = userService.getCustomerProfile(customerId);
//        ProfileDTO profile = userService.getCustomerProfile(userDetails.getUserId());
		
		ModelAndView view = new ModelAndView("control_template");
		view.addObject("page", "CONTRACTS");
		view.addObject("user", "CUSTOMER");
		view.addObject("contracts", contractService.getCustomerContracts(userDetails.getUserId()));
		return view;
	}
}
