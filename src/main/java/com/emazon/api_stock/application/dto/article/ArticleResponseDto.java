package com.emazon.api_stock.application.dto.article;

import com.emazon.api_stock.application.dto.brand.BrandArticleResponseDto;
import com.emazon.api_stock.application.dto.category.CategoryResponseListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
