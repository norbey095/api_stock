package com.emazon.api_stock.infraestructure.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    URL_CREATE_CATEGORY("/stock/category/createCategory"),
    URL_GET_CATEGORY("/stock/category/getCategorys"),
    JSON_REQUEST("{\"name\": \"Electronics\", \"description\": \"Category for electronics\"}"),
    CATEGORY_EXISTS("There is already a category with that name"),
    INVALID_CATEGORY_NAME("Invalid argument"),
    INVALID_CATEGORY_DESCRIPTION("Invalid argument"),
    NO_DATA_FOUND_EXCEPTION_MESSAGE("No data found in the database"),
    NEGATIVE_NOT_ALLOWED("The page and size fields cannot be negative.");

    private final String message;

    ConstantsTest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
