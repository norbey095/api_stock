package com.emazon.api_stock.application.dto.article;

import lombok.Data;

import java.util.List;

@Data
public class ArticleCartRequestDto {

    private Integer page;
    private Integer size;
    private boolean descending;
    private List<Integer> articleIds;
    private String categoryName;
    private String brandName;
}
