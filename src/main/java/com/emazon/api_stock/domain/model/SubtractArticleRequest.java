package com.emazon.api_stock.domain.model;

public class SubtractArticleRequest {

    private Integer id;
    private Integer quantity;

    public SubtractArticleRequest() {
    }

    public SubtractArticleRequest(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

