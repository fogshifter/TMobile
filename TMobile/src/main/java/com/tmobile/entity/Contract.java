package com.tmobile.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "contract")
public class Contract {

	private int id;
	private String phone;

	private User customer;
	
	private Tariff tariff;
	
	private List<Option> options = new ArrayList<Option>();
	
	public Contract() {
	}
	
	public Contract(String phone) {
		this.phone = phone;
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "tariff_id")
	public Tariff getTariff() {
		return tariff;
	}

	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // cascade ?
	@JoinTable(name = "contract_options")
	public List<Option> getOptions() {
		return options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public void addOption(Option option) {
		this.options.add(option);
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	public User getCustomer() {
		return customer;
	}
	
	@Column(name = "p_number")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
