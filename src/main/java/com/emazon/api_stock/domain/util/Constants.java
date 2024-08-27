package com.emazon.api_stock.domain.util;

public enum Constants {

    FIELD_NAME_NULL("The 'name' field cannot be empty."),
    FIELD_NAME_MAX("The 'name' field cannot exceed 50 characters."),
    FIELD_DESCRIPTION_NULL("The 'description' field cannot be empty"),
    FIELD_DESCRIPTION_CATEGORY_MAX("The 'description' field cannot exceed 90 characters"),
    FIELD_DESCRIPTION_BRAND_MAX("The 'description' field cannot exceed 120 characters");

    private final String message;

    Constants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
