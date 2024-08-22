package com.emazon.api_stock.infraestructure.exceptionhandler;

public class ExceptionResponse {
    private final String message;
    private final String status;

    public ExceptionResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
