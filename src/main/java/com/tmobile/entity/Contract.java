package com.tmobile.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "contract")
public class Contract {

    public enum eBlocked {
        NOT_BLOCKED(0),
        BLOCKED_BY_CUSTOMER(1),
        BLOCKED_BY_MANAGER(2);

        private final int value;
        private eBlocked(int val) {
            this.value = val;
        }

        public int getVal() {
            return this.value;
        }
    }

    private int id;
    private String phone;

    private User customer;

    private Tariff tariff;

    private eBlocked blocked = eBlocked.NOT_BLOCKED;

    private List<Option> options = new ArrayList<>();

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id")
    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY) // cascade ?
    @JoinTable(
        name = "contract_options",
        joinColumns = { @JoinColumn(name = "contract_id") },
        inverseJoinColumns = { @JoinColumn(name = "option_id")})
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

    @ManyToOne(fetch = FetchType.LAZY)
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


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "blocked")
    public eBlocked getBlocked() {
        return this.blocked;
    }

    public void setBlocked(eBlocked blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contract)) return false;
        Contract contract = (Contract) o;
        return id == contract.id &&
                Objects.equals(phone, contract.phone) &&
                Objects.equals(customer, contract.customer) &&
                Objects.equals(tariff, contract.tariff) &&
                blocked == contract.blocked;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, phone, customer, tariff, blocked);
    }
}
