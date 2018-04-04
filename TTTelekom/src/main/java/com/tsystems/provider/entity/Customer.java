package com.tsystems.provider.entity;

import java.util.Date;

//import org.hibernate.loader.entity.*;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer")
public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String password; // TODO: is this suitable ?
	private String address;
	private String email;
	
	public Customer() {
	}
	
	public Customer(String firstName, String lastName, Date birthDate, String password, String address, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.password = password;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
//	@Temporal(TemporalType)
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date date) {
		this.birthDate = date;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
