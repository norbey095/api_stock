package com.emazon.api_stock.domain.model;

public class Category {

    private static final String MSN_NAME = "The name must be less than 50 characters.";
    private static final String MSN_DESCRIPTION = "The description must be less than 90 characters.";

    private Integer id;
    private String name;
    private String description;

    public Category(Integer id, String name, String description) {
        if (name == null || name.length() > 50) {
            throw new IllegalArgumentException(MSN_NAME);
        }
        if (description == null || description.length() > 90) {
            throw new IllegalArgumentException(MSN_DESCRIPTION);
        }
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
