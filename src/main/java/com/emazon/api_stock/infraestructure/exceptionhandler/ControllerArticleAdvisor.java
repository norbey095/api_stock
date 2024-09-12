package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.domain.exception.article.ArticleAlreadyExistsException;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryException;
import com.emazon.api_stock.domain.exception.article.InvalidArticleCategoryNumberException;
import com.emazon.api_stock.domain.exception.article.RepeatedCategoryException;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerArticleAdvisor {

    @ExceptionHandler(ArticleAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleArticleAlreadyExistsException(ArticleAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                InfraestructureConstants.ARTICLE_ALREADY_EXISTS,
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(InvalidArticleCategoryException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArticleCategoryException
            (InvalidArticleCategoryException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(InvalidArticleCategoryNumberException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArticleCategoryNumberException
            (InvalidArticleCategoryNumberException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(RepeatedCategoryException.class)
    public ResponseEntity<ExceptionResponse> handleRepeatedCategoryException
            (RepeatedCategoryException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }
}