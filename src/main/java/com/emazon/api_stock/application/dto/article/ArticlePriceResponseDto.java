package com.emazon.api_stock.application.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticlePriceResponseDto {

    private Integer id;
    private double price;
    private Integer quantity;
}
