package com.tmobile.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_option")
public class Option {

    private int id = 0;
    private int payment;
    private int price;
    private String name;
    private String description;
    private boolean compatible = true;

//    private List<TariffOptions> compatibleTariffOptions = new ArrayList<>();
    private List<Tariff> compatibleTariffs = new ArrayList<>();

    private List<Option> compatibleOptions = new ArrayList<>();
    private List<Option> requiredOptions = new ArrayList<>();
    private List<Contract> optionContracts = new ArrayList<>();

    private List<Option> compatibleByOptions = new ArrayList<>();
    private List<Option> requiredByOptions = new ArrayList<>();

    @ManyToMany(mappedBy = "compatibleOptions")
    public List<Tariff> getCompatibleTariffs() {
        return this.compatibleTariffs;
    }

    public void setCompatibleTariffs(List<Tariff> tariffs) {
        this.compatibleTariffs = tariffs;
    }


    @ManyToMany(mappedBy="options")
    public List<Contract> getOptionContracts() {
        return optionContracts;
    }

    public void setOptionContracts(List<Contract> contracts) {
        this.optionContracts = contracts;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public void removeCompatibleOption(Option o) {
        this.compatibleOptions.remove(o);
    }


    @ManyToMany(mappedBy = "compatibleOptions")
    public List<Option> getCompatibleByOptions() {
        return compatibleByOptions;
    }

    public void setCompatibleByOptions(List<Option> options) {
        this.compatibleByOptions = options;
    }

    @ManyToMany(mappedBy = "requiredOptions")
    public List<Option> getRequiredByOptions() {
        return requiredByOptions;
    }

    public void setRequiredByOptions(List<Option> options) {
        this.requiredByOptions = options;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public void removeRequiredOption(Option o) {
        this.requiredOptions.remove(o);
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
                Objects.equals(description, option.description) &&
                Objects.equals(compatibleTariffs, option.compatibleTariffs) &&
                Objects.equals(compatibleOptions, option.compatibleOptions) &&
                Objects.equals(requiredOptions, option.requiredOptions) &&
                Objects.equals(optionContracts, option.optionContracts) &&
                Objects.equals(compatibleByOptions, option.compatibleByOptions) &&
                Objects.equals(requiredByOptions, option.requiredByOptions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, payment, price, name, description, compatible, compatibleTariffs, compatibleOptions, requiredOptions, optionContracts, compatibleByOptions, requiredByOptions);
    }
}
