package com.emazon.api_stock.domain.util;

public class CategoryConstants {

    private CategoryConstants() {
        throw new IllegalArgumentException("Utility class");
    }

    public static final String FIELD_NAME_MESSAGE = "El nombre no puede ser vacío y " +
            "no puede superar los 50 caracteres.";
    public static final String FIELD_DESCRIPTION_MESSAGE = "La descripción no puede ser vacía y " +
            "no puede superar los 90 caracteres.";
}
