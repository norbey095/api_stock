package com.emazon.api_stock.infraestructure.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    FIELD_SEARCH_NAME("Book"),
    URL("/stock/category/createCategory"),
    JSON_REQUEST("{\"name\": \"Electronics\", \"description\": \"Category for electronics\"}"),
    CATEGORY_EXISTS("Ya existe una categoría con ese nombre"),
    INVALID_CATEGORY_NAME("Invalid argument"),
    INVALID_CATEGORY_DESCRIPTION("Invalid argument");

    private final String message;

    ConstantsTest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
