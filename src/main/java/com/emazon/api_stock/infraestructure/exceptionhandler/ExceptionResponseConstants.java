package com.emazon.api_stock.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponseConstants {
    CATEGORY_ALREADY_EXISTS("There is already a category with that name"),
    NO_DATA_FOUND_EXCEPTION_MESSAGE("No data found in the database"),
    NEGATIVE_NOT_ALLOWED("The page and size fields cannot be negative.");

    private final String message;

    ExceptionResponseConstants(String message) {
        this.message = message;
    }

}
