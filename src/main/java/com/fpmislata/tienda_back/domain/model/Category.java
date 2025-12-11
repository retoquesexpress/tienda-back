package com.fpmislata.tienda_back.domain.model;

public class Category {
    private String id_category;
    private String name;

    public Category(String id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    public String getId() {
        return id_category;
    }

    public void setId(String id) {
        this.id_category = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
