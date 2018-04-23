package com.tmobile.dto;

import java.util.HashMap;
//import java.util.List;
import java.util.Map;

public class ProfileDTO {

//	private String customerName;
	private String firstName;
	private String lastName;
	private String email;
	private String number;
	private String price;
	private Map<Integer, String> tariffs = new HashMap<Integer, String>();
	private Map<String, String> options = new HashMap<String, String>();
	
	public ProfileDTO() {
		
	}
	
	public Map<String, String> getOptions() {
		return options;
	}

	/*public void setOptions(Map<String, String> options) {
		this.options = options;
	}*/
	public void addOption(String option, String price) {
		options.put(option, price);
	}

/*	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}*/
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Map<Integer, String> getTariff() {
		return tariffs;
	}

	public void addTariff(Integer id, String tariff) {
		tariffs.put(id, tariff);
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
