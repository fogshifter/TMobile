package com.tmobile.dto;

import java.util.ArrayList;
import java.util.List;

public class TariffDTO {
    private int id;
    private String name;
    private String description;
    private int price;
    private List<Integer> compatibleOptions = new ArrayList<>();

    public TariffDTO() {}

//    public TariffDTO(int id, String name, String description, int price, List<Integer> compatibleOptions) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.compatibleOptions = compatibleOptions;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Integer> getCompatibleOptions() {
        return compatibleOptions;
    }

    public void setCompatibleOptions(List<Integer> compatibleOptions) {
        this.compatibleOptions = compatibleOptions;
    }
}
