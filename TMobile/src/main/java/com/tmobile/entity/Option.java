package com.tmobile.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_option")
public class Option {
	
	private int id;
	private int payment;
	private int price;
	private String name;
	private String description;
	boolean compatible;

	private List<TariffOptions> compatibleTariffs = new ArrayList<>();
//	private List<OptionOptions> compatibleOptions =

	@OneToMany(mappedBy = "option", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<TariffOptions> getCompatibleTariffs() {
        return compatibleTariffs;
    }

    public void setCompatibleTariffs(List<TariffOptions> tariffOptions) {
	    this.compatibleTariffs = tariffOptions;
    }

//    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL, orphanRemoval = true)
//    public List

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

	@Column(name = "name")
    public String getName() {
	    return name;
    }

    public void setName(String name) {
	    this.name = name;
    }

	@Column(name = "description")
    @Type(type = "text")
    public String getDescription() {
	    return description;
    }

    public void setDescription(String desc) {
	    this.description = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id == option.id &&
                payment == option.payment &&
                price == option.price &&
                compatible == option.compatible &&
                Objects.equals(description, option.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, payment, price, compatible, description);
    }
}
