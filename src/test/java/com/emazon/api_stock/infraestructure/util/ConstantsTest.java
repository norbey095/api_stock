package com.emazon.api_stock.infraestructure.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    FIELD_SEARCH_NAME("Book"),
    URL_CREATE_CATEGORY("/stock/category/createCategory"),
    URL_GET_CATEGORY("/stock/category/getCategorys"),
    JSON_REQUEST("{\"name\": \"Electronics\", \"description\": \"Category for electronics\"}"),
    CATEGORY_EXISTS("Ya existe una categoría con ese nombre"),
    INVALID_CATEGORY_NAME("Invalid argument"),
    INVALID_CATEGORY_DESCRIPTION("Invalid argument"),
    NO_DATA_FOUND_EXCEPTION_MESSAGE("No se encontraron datos en la base de datos"),
    NEGATIVE_NOT_ALLOWED("Los campos page y size no pueden ser negativos.");

    private final String message;

    ConstantsTest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
