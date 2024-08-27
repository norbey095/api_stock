package com.emazon.api_stock.domain.exception.brand;

public class InvalidBrandNameException extends RuntimeException{
    public InvalidBrandNameException(String message){
        super(message);
    }
}
