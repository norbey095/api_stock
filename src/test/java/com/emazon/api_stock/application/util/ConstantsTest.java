package com.emazon.api_stock.application.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    FIELD_BRAND_DESCRIPTION("Brand for electronics"),
    FIELD_ARTICLE_DESCRIPTION("Article for electronics"),
    ARTICLE("article");

    private final String message;

    ConstantsTest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
