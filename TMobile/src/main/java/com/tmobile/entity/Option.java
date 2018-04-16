package com.tmobile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_option")
public class Option {
	
	private int id;
	private int payment;
	private int price;
	boolean compatible;
	
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
	
	@Column(name = "payment")
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	
	@Column(name = "price")
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Column(name = "compatible")
	public boolean isCompatible() {
		return compatible;
	}
	public void setCompatible(boolean compatible) {
		this.compatible = compatible;
	}
}
