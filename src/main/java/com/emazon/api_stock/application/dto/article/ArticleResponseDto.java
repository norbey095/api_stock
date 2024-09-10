package com.emazon.api_stock.application.dto.article;

import com.emazon.api_stock.application.dto.brand.BrandArticleResponseDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseListDto;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDto {

    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
    private BrandArticleResponseDto brand;
    private List<CategoryResponseListDto> categories;
}
