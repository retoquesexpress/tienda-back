package com.fpmislata.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_category;
    private String name;

    public CategoryJpaEntity() {
    }

    public CategoryJpaEntity(Integer id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryJpaEntity other)) {
            return false;
        }
        return (this.id_category != null || other.id_category == null)
                && (this.id_category == null || this.id_category.equals(other.id_category));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_category != null ? id_category.hashCode() : 0);
        return hash;
    }
}
