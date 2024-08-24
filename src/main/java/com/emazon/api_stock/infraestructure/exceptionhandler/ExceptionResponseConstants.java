package com.emazon.api_stock.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponseConstants {
    CATEGORY_ALREADY_EXISTS("Ya existe una categoría con ese nombre"),
    NO_DATA_FOUND_EXCEPTION_MESSAGE("No se encontraron datos en la base de datos"),
    NEGATIVE_NOT_ALLOWED("Los campos page y size no pueden ser negativos.");

    private final String message;

    ExceptionResponseConstants(String message) {
        this.message = message;
    }

}
