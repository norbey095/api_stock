package com.emazon.api_stock.domain.exception.article;

public class InvalidArticleCategoryNumberException extends RuntimeException{
    public InvalidArticleCategoryNumberException(String message){
        super(message);
    }
}
