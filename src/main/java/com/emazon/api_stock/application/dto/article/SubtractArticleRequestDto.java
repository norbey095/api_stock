package com.emazon.api_stock.application.dto.article;

import lombok.Data;

@Data
public class SubtractArticleRequestDto {

    private Integer articleId;
    private Integer quantity;
}
