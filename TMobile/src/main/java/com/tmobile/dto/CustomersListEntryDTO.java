package com.tmobile.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomersListEntryDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
    private String birthDate;

	private List<String> phones = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
	
	public List<String> getPhones() {
		return phones;
	}
	public void addPhone(String phone) {
		phones.add(phone);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
