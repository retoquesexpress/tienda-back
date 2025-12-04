package com.fpmislata.tienda_back.domain.model;

import java.util.List;

public class Service {
    private String id_service;
    private String name;
    private String description;
    private double price;
    private String pictureUrl;

    public Service(String id_service, String name, String description, double price, String pictureUrl) {
        this.id_service = id_service;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return id_service;
    }

    public void setId(String id_service) {
        this.id_service = id_service;
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
}
