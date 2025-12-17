package com.fpmislata.tienda_back.domain.model;

public class Category {
    private Integer id_category;
    private String name;

    public Category(Integer id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    public Integer getId() {
        return id_category;
    }

    public void setId(Integer id) {
        this.id_category = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
