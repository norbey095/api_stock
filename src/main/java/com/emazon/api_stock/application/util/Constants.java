package com.emazon.api_stock.application.util;

import lombok.Getter;

@Getter
public class Constants {
    public static final String NAME_REQUIRED = "The parameter 'name' cannot be empty";
    public static final String DESCRIPTION_REQUIRED = "The parameter 'description' cannot be empty";
    public static final String QUANTITY_NOT_NULL = "The quantity cannot be null";
    public static final String QUANTITY_NOT_NEGATIVE = "The quantity cannot be negative";
    public static final String PRICE_NOT_NULL = "The price cannot be null";
    public static final String PRICE_NOT_NEGATIVE = "The price cannot be negative";
    public static final String BRAND_REQUIRED = "The parameter 'brand' cannot be empty";

    private Constants() {

    }
}
