package com.emazon.api_stock.domain.exception;

public class InvalidCategoryNameException extends RuntimeException{
    public InvalidCategoryNameException(String message){
        super(message);
    }
}
