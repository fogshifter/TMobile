package com.tmobile.dto;

public class CustomerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String address;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public String getBirthDate() {
        return this.birthDate;
    }
}
