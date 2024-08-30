package com.emazon.api_stock.domain.exception.article;

public class RepeatedCategoryException extends RuntimeException{
    public RepeatedCategoryException(String message){
        super(message);
    }
}
