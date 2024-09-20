package com.emazon.api_stock.application.util;

import lombok.Getter;

@Getter
public class ConstantsMapper {
    public static final String NAME_REQUIRED = "The parameter 'name' cannot be empty";
    public static final String DESCRIPTION_REQUIRED = "The parameter 'description' cannot be empty";
    public static final String QUANTITY_NOT_NULL = "The quantity cannot be null";
    public static final String QUANTITY_NOT_NEGATIVE = "The quantity cannot be negative";
    public static final String PRICE_NOT_NULL = "The price cannot be null";
    public static final String PRICE_NOT_NEGATIVE = "The price cannot be negative";
    public static final String BRAND_REQUIRED = "The parameter 'brand' cannot be empty";
    public static final String MESSAGES_SUCCESS = "Article created successfully";
    public static final String BRAND_MESSAGES_SUCCESS = "Brand created successfully";
    public static final String CATEGORY_MESSAGES_SUCCESS = "Category created successfully";
    public static final String BRAND = "brand";
    public static final String CATEGORIES = "categories";
    public static final String ID = "id";
    public static final String ID_NOT_NULL = "The id cannot be null";
    public static final String UPDATE_SUCCESS = "The amount was updated correctly";
    public static final int VALUE_0 = 0;

    private ConstantsMapper() {

    }
}
