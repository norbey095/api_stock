package com.emazon.api_stock.domain.model;

import java.util.List;

public class ArticleSave {

    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private Integer idbrand;
    private List<Integer> categories;

    public ArticleSave(Integer id, String name, String description, Integer quantity, double price, Integer idbrand,
                       List<Integer> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.idbrand = idbrand;
        this.categories = categories;
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

    public Integer getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Integer getIdbrand() {
        return idbrand;
    }

    public List<Integer> getCategories() {
        return categories;
    }
}
