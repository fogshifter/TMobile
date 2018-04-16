package com.tmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tmobile.dto.CustomerDTO;
import com.tmobile.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	private ManagerService managerService;
	
	@Autowired
	public ManagerController(ManagerService service) {
		this.managerService = service;
	}
	
	@GetMapping
	public String controlPanel() {
		return "control_panel";
	}

	/*@GetMapping("users")
	public ModelAndView usersList() {
		ModelAndView view = new ModelAndView("usersList");
//		view.addObject()
		view.addObject("customers", managerService.getCustomersList());
		return view;
	}*/
}
