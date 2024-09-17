package com.emazon.api_stock.application.dto.article;

import com.emazon.api_stock.application.util.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleUpdateRequestDto {

    @NotNull(message = Constants.ID_NOT_NULL)
    private Integer id;
    @NotNull(message = Constants.QUANTITY_NOT_NULL)
    @Min(value = Constants.VALUE_0, message = Constants.QUANTITY_NOT_NEGATIVE)
    private Integer quantity;
}
