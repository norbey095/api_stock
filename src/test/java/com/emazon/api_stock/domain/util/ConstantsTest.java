package com.emazon.api_stock.domain.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    FIELD_BRAND_DESCRIPTION("brand for electronics"),
    NAME_INVALID("This name is way too long and should trigger an exception"),
    MSN_NAME_CARACTER("The 'name' field cannot exceed 50 characters."),
    MSN_NAME_NULL("The 'name' field cannot be empty."),
    DESCRIPTION_INVALID("This description is way too long and exceeds the maximum " +
            "allowed length of 90 characters..."),
    DESCRIPTION_BRAND_INVALID("This description is too long and exceeds the maximum allowed " +
            "length of 120 characters. Please adjust the description comment to make it a valid length."),
    MSN_DESCRIPTION_CARACTER("The 'description' field cannot exceed 90 characters"),
    MSN_DESCRIPTION_BRAND_CARACTER("The 'description' field cannot exceed 120 characters"),
    MSN_DESCRIPTION_NULL("The 'description' field cannot be empty"),
    FIELD_ARTICLE_DESCRIPTION("article for electronics");

    private final String message;

    ConstantsTest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
