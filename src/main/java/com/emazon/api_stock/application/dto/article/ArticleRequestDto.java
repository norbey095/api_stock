package com.emazon.api_stock.application.dto.article;

import com.emazon.api_stock.application.util.Constants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ArticleRequestDto {

    @NotBlank(message = Constants.NAME_REQUIRED)
    private String name;
    @NotBlank(message = Constants.DESCRIPTION_REQUIRED)
    private String description;
    @NotNull(message = Constants.QUANTITY_NOT_NULL)
    @Min(value = Constants.VALUE_0, message = Constants.QUANTITY_NOT_NEGATIVE)
    private Integer quantity;
    @NotNull(message = Constants.PRICE_NOT_NULL)
    @Min(value = Constants.VALUE_0, message = Constants.PRICE_NOT_NEGATIVE)
    private double price;
    @NotNull(message = Constants.BRAND_REQUIRED)
    private Integer idbrand;
    private List<Integer> categories;
}
