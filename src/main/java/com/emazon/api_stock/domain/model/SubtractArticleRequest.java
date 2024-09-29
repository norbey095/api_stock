package com.emazon.api_stock.domain.model;

public class SubtractArticleRequest {

    private Integer articleId;
    private Integer quantity;

    public SubtractArticleRequest() {
    }

    public SubtractArticleRequest(Integer articleId, Integer quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

