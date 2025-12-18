package com.fpmislata.tienda_back.domain.model;

public class Category {
    private Integer idCategory;
    private String name;

    public Category(Integer idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public Integer getId() {
        return idCategory;
    }

    public void setId(Integer id) {
        this.idCategory = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
