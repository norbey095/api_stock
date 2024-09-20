package com.emazon.api_stock.application.dto.article;

import com.emazon.api_stock.application.util.ConstantsDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ArticleRequestDto {

    @NotBlank(message = ConstantsDto.NAME_REQUIRED)
    private String name;
    @NotBlank(message = ConstantsDto.DESCRIPTION_REQUIRED)
    private String description;
    @NotNull(message = ConstantsDto.QUANTITY_NOT_NULL)
    @Min(value = ConstantsDto.NUMBER_0, message = ConstantsDto.QUANTITY_NOT_NEGATIVE)
    private Integer quantity;
    @NotNull(message = ConstantsDto.PRICE_NOT_NULL)
    @Min(value = ConstantsDto.NUMBER_0, message = ConstantsDto.PRICE_NOT_NEGATIVE)
    private double price;
    @NotNull(message = ConstantsDto.BRAND_REQUIRED)
    private Integer idbrand;
    private List<Integer> categories;
}
