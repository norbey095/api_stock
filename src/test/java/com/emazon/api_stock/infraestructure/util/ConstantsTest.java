package com.emazon.api_stock.infraestructure.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    FIELD_BRAND_DESCRIPTION("brand for electronics"),
    URL_CREATE_CATEGORY("/stock/category/createCategory"),
    URL_CREATE_BRAND("/stock/brand/createBrand"),
    URL_GET_CATEGORY("/stock/category/getCategorys"),
    URL_GET_BRAND("/stock/brand/getBrands"),
    JSON_REQUEST("{\"name\": \"Electronics\", \"description\": \"Category for electronics\"}"),
    JSON_BRAND_REQUEST("{\"name\": \"Electronics\", \"description\": \"brand for electronics\"}"),
    CATEGORY_EXISTS("There is already a category with that name"),
    BRAND_EXISTS("There is already a brand with that name"),
    INVALID_NAME("Invalid argument"),
    INVALID_DESCRIPTION("Invalid argument"),
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
