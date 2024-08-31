package com.emazon.api_stock.domain.model;

public class ArticleXCategory {

    private Integer id;
    private Integer idcategory;
    private Integer idarticles;

    public ArticleXCategory() {
    }

    public ArticleXCategory(Integer id, Integer idcategory, Integer idarticles) {
        this.id = id;
        this.idcategory = idcategory;
        this.idarticles = idarticles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public Integer getIdarticles() {
        return idarticles;
    }

    public void setIdarticles(Integer idarticles) {
        this.idarticles = idarticles;
    }
}
