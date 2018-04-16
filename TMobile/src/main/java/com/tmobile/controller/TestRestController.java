package com.tmobile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class TestRestController {

	@GetMapping
	public String getRest() {
		return "{ 'asd' : 1 }";
	}
}
