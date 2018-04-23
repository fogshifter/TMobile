package com.tmobile.dto;


public class ContractsListEntryDTO {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String tariff;
	private String phone;

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
        return phone;
	}

	public void setPhone(String phone) {
        this.phone = phone;
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
