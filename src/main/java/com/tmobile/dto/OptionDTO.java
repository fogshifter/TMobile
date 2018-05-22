package com.tmobile.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OptionDTO {
    private int id;
    private int payment;
    private int price;
    private String description;
    private String name;
    boolean compatible;


//    boolean required;//
    List<Integer> compatibleOptions = new ArrayList<>();
    List<Integer> requiredOptions = new ArrayList<>();

    public List<Integer> getCompatibleOptions() {
        return compatibleOptions;
    }

    public void setCompatibleOptions(List<Integer> optionsIds) {
        compatibleOptions = optionsIds;
    }

    public void addCompatibleOption(int id) {
        compatibleOptions.add(id);
    }

    public List<Integer> getRequiredOptions() {
        return requiredOptions;
    }

    public void setRequiredOptions(List<Integer> optionsIds) {
        requiredOptions = optionsIds;
    }

    public void addRequiredOptions(int id) {
        requiredOptions.add(id);
    }
//    List<OptionDTO> requiredOptions = new ArrayList<>();

//    public List<OptionDTO> getRequiredOptions() {
//        return requiredOptions;
//    }
//
//    public void setRequiredOptions(List<OptionDTO> requiredOptions) {
//        this.requiredOptions = requiredOptions;
//    }
//
//    public List<OptionDTO> getCompatibleOptions() {
//        return compatibleOptions;
//    }
//
//    public void addCompatibleOption(OptionDTO option) {
//
//        this.compatibleOptions.add(option);
//    }
//
//    public List<OptionDTO> getRequiredOptions() {
//        return requiredOptions;
//    }
//
//    public void addRequiredOptions(OptionDTO option) {
//        this.requiredOptions.add(option);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompatible() {
        return compatible;
    }

    public void setCompatible(boolean compatible) {
        this.compatible = compatible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
