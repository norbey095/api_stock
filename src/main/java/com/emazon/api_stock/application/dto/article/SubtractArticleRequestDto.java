package com.emazon.api_stock.application.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubtractArticleRequestDto {

    private Integer articleId;
    private Integer quantity;
}
