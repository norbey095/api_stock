package com.emazon.api_stock.domain.exception.article;

public class InvalidArticleCategoryException extends RuntimeException{
    public InvalidArticleCategoryException(String message){
        super(message);
    }
}
