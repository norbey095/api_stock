package com.emazon.api_stock.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("Ya existe una categoría con ese nombre");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

}
