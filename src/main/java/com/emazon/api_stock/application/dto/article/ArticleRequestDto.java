package com.emazon.api_stock.application.dto.article;

import lombok.Data;

import java.util.List;

@Data
public class ArticleRequestDto {

    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private Integer idbrand;
    private List<Integer> categorys;
}
