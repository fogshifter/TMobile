package com.tmobile.dto;

import com.tmobile.util.Types;
import org.modelmapper.internal.TypeMapStore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class ContractInfoDTO {
    private int customerId;
    private int tariffId;
    private int contractId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String passportData;
    private int blocked;
//    private Types.ContractBlocked blocked;
    private List<Integer> optionIds = new ArrayList<>();

    public ContractInfoDTO() {
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public List<Integer> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<Integer> optionIds) {
        this.optionIds = optionIds;
    }

    public void addOptionId(Integer id) {
        optionIds.add(id);
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String data) {
        this.passportData = data;
    }

    public int getBlocked() {
        return this.blocked;
    }
//    public Types.ContractBlocked getBlocked() {
//    return this.blocked;
//}

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }
//    public void setBlocked(Types.ContractBlocked blocked) {
//    this.blocked = blocked;
//}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractInfoDTO that = (ContractInfoDTO) o;
        return customerId == that.customerId &&
                tariffId == that.tariffId &&
                contractId == that.contractId &&
                blocked == that.blocked &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(password, that.password) &&
                Objects.equals(passportData, that.passportData) &&
                Arrays.equals(optionIds.toArray(), that.optionIds.toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, tariffId, contractId, firstName, lastName, birthDate, email, address,
                phone, password, passportData, blocked, optionIds);
    }
}
