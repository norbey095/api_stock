package com.emazon.api_stock.domain.util;

public enum CategoryConstants {

    FIELD_NAME_NULL("El campo 'nombre' no puede ser vacío."),
    FIELD_NAME_MAX("El campo 'nombre' no puede superar los 50 caracteres."),
    FIELD_DESCRIPTION_NULL("El campo 'descripcion' no puede ser vacío"),
    FIELD_DESCRIPTION_MAX("El campo 'descripcion' no puede superar los 90 caracteres");

    private final String message;

    CategoryConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
