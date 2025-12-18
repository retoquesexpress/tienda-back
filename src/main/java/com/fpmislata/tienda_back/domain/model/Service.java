package com.fpmislata.tienda_back.domain.model;

import java.util.List;

public class Service {
    private Integer idService;
    private String name;
    private String description;
    private double price;
    private String pictureUrl;
    private Category category;

    public Service(Integer idService, String name, String description, double price, String pictureUrl,
            Category category) {
        this.idService = idService;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.category = category;
    }

    public Integer getId() {
        return idService;
    }

    public void setId(Integer idService) {
        this.idService = idService;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
