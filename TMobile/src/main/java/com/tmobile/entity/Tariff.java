package com.tmobile.entity;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "tariff")
public class Tariff {

    private int id;
    private String name;
    private String description;
    private int price;
    private boolean defaultTariff = false;

//    private List<TariffOptions> compatibleOptions = new ArrayList<>();
    private List<Option> compatibleOptions = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tariff_options",
            joinColumns = { @JoinColumn(name = "tariff_id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id") }
    )
    public List<Option> getCompatibleOptions() {
        return this.compatibleOptions;
    }

    public void setCompatibleOptions(List<Option> options) {
        this.compatibleOptions = options;
    }
    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<TariffOptions> getCompatibleOptions() {
        return compatibleOptions;
    }

    // TODO: use @ManyToMany instead of TariffOptions
    public void setCompatibleOptions(List<TariffOptions> tariffOptions) {
        this.compatibleOptions = tariffOptions;
    }

    public void addCompatibleOption(Option option) {
        TariffOptions tariffOption = new TariffOptions(this, option);

        compatibleOptions.add(tariffOption);
        option.getCompatibleTariffOptions().add(tariffOption);
    }

    public void removeCompatibleOption(Option option) {
        TariffOptions tariffOptions = new TariffOptions(this, option);
        option.getCompatibleTariffOptions().remove(tariffOptions);
        compatibleOptions.remove(tariffOptions);
        tariffOptions.setOption(null);
        tariffOptions.setTariff(null);
    }*/

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

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "default_flag")
    public boolean isDefaultTariff() {
        return defaultTariff;
    }

    public void setDefaultTariff(boolean flag) {
        this.defaultTariff = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariff)) return false;
        Tariff tariff = (Tariff) o;
        return id == tariff.id &&
                price == tariff.price &&
                Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, defaultTariff);
    }
}
