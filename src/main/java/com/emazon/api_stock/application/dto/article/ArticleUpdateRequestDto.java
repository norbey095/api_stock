package com.emazon.api_stock.application.dto.article;

import com.emazon.api_stock.application.util.ConstantsDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ArticleUpdateRequestDto {

    @NotNull(message = ConstantsDto.ID_NOT_NULL)
    private Integer articleId;
    @NotNull(message = ConstantsDto.QUANTITY_NOT_NULL)
    @Min(value = ConstantsDto.NUMBER_0, message = ConstantsDto.QUANTITY_NOT_NEGATIVE)
    private Integer quantity;
}
