package com.emazon.api_stock.domain.util;

public enum ConstantsTest {

    FIELD_NAME("Electronics"),
    FIELD_DESCRIPTION("Category for electronics"),
    NAME_INVALID("This name is way too long and should trigger an exception"),
    MSN_NAME_CARACTER("El campo 'nombre' no puede superar los 50 caracteres."),
    MSN_NAME_NULL("El campo 'nombre' no puede ser vacío."),
    DESCRIPTION_INVALID("This description is way too long and exceeds the maximum " +
            "allowed length of 90 characters..."),
    MSN_DESCRIPTION_CARACTER("El campo 'descripcion' no puede superar los 90 caracteres"),
    MSN_DESCRIPTION_NULL("El campo 'descripcion' no puede ser vacío");

    private final String message;

    ConstantsTest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
