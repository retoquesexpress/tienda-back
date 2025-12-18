package com.fpmislata.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer idCategory;
    private String name;

    public CategoryJpaEntity() {
    }

    public CategoryJpaEntity(Integer idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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
        return (this.idCategory != null || other.idCategory == null)
                && (this.idCategory == null || this.idCategory.equals(other.idCategory));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategory != null ? idCategory.hashCode() : 0);
        return hash;
    }
}
