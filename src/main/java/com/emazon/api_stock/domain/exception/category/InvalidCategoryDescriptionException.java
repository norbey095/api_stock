package com.emazon.api_stock.domain.exception.category;

public class InvalidCategoryDescriptionException extends RuntimeException{
    public InvalidCategoryDescriptionException(String message){
        super(message);
    }
}
