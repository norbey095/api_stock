package com.emazon.api_stock.domain.model;

public class Brand {
    private Integer id;
    private String name;
    private String description;


    public Brand(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
