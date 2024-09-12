package com.emazon.api_stock.domain.util;

public class Constants {

    public static final String FIELD_NAME_NULL = "The 'name' field cannot be empty.";
    public static final String FIELD_NAME_MAX = "The 'name' field cannot exceed 50 characters.";
    public static final String FIELD_DESCRIPTION_NULL = "The 'description' field cannot be empty";
    public static final String FIELD_DESCRIPTION_CATEGORY_MAX = "The 'description' field cannot exceed 90 characters";
    public static final String FIELD_DESCRIPTION_BRAND_MAX = "The 'description' field cannot exceed 120 characters";
    public static final String FIELD_CATEGORIES_NULL = "The 'categories' field cannot be empty.";
    public static final String FIELD_CATEGORIES_INVALID_NUMBER = "An article cannot have more than 3 categories.";
    public static final String REPEATED_CATEGORIES = "Categories cannot be repeated";
    public static final Integer VALUE_3 = 3;
    public static final Integer VALUE_1 = 1;
    public static final Integer VALUE_120 = 120;
    public static final Integer VALUE_50 = 50;
    public static final Integer VALUE_90 = 90;
    public static final Integer VALUE_0 = 0;

    private Constants() {
    }
}
