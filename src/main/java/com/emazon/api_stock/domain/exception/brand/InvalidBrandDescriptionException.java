package com.emazon.api_stock.domain.exception.brand;

public class InvalidBrandDescriptionException extends RuntimeException{
    public InvalidBrandDescriptionException(String message){
        super(message);
    }
}
