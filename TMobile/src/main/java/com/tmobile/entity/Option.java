package com.tmobile.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.*;

@Entity
@Table(name = "t_option")
public class Option {

    private int id;
    private int payment;
    private int price;
    private String name;
    private String description;
    private boolean compatible = true;

//    private List<TariffOptions> compatibleTariffOptions = new ArrayList<>();
    private List<Tariff> compatibleTariffs = new ArrayList<>();

    private List<Option> compatibleOptions = new ArrayList<>();
    private List<Option> requiredOptions = new ArrayList<>();

    @ManyToMany(mappedBy = "compatibleOptions")
    public List<Tariff> getCompatibleTariffs() {
        return this.compatibleTariffs;
    }

    public void setCompatibleTariffs(List<Tariff> tariffs) {
        this.compatibleTariffs = tariffs;
    }

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "option", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<TariffOptions> getCompatibleTariffOptions() {
        return compatibleTariffOptions;
    }

    public void addDefaultTariffOptions(TariffOptions tariffOptions) {
        this.compatibleTariffOptions.add(tariffOptions);
    }

    public void setCompatibleTariffOptions(List<TariffOptions> tariffOptions) {
        this.compatibleTariffOptions = tariffOptions;
    }*/

    @ManyToMany(cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "options_compat",
               joinColumns = {@JoinColumn(name = "option_id")},
               inverseJoinColumns = {@JoinColumn(name = "compat_option_id")})
    public List<Option> getCompatibleOptions() {
        return this.compatibleOptions;
    }

    public void setCompatibleOptions(List<Option> compatibleOptions) {
        this.compatibleOptions = compatibleOptions;
    }

    public void addCompatibleOption(Option compatibleOption) {
        this.compatibleOptions.add(compatibleOption);
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "options_required",
               joinColumns = {@JoinColumn(name = "option_id")},
               inverseJoinColumns = {@JoinColumn(name = "required_option_id")})
    public List<Option> getRequiredOptions() {
        return this.requiredOptions;
    }

    public void setRequiredOptions(List<Option> requiredOptions) {
        this.requiredOptions = requiredOptions;
    }

    public void addRequiredOptions(Option requiredOption) {
        this.requiredOptions.add(requiredOption);
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

    @Column(name = "compatible")
    @Type(type = "boolean")
    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
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
                Objects.equals(name, option.name) &&
                Objects.equals(description, option.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, payment, price, name, description, compatible);
    }
}
