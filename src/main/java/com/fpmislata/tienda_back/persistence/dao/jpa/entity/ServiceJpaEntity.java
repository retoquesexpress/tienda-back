package com.fpmislata.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_service;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;


    public ServiceJpaEntity() {
    }

    public ServiceJpaEntity (String id_service, String name, String description, Double price, String pictureUrl) {
        this.id_service = id_service;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
    }

    public String getId_service() {
        return id_service;
    }
    public void setId_service(String id_service) {
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ServiceJpaEntity other)) {
            return false;
        }
        return (this.id_service != null || other.id_service == null) && (this.id_service == null || this.id_service.equals(other.id_service));
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_service != null ? id_service.hashCode() : 0);
        return hash;
    }

}
