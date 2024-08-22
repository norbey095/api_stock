package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.domain.exception.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.InvalidCategoryNameException;
import com.emazon.api_stock.infraestructure.exception.CategoryAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.CATEGORY_ALREADY_EXISTS.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(InvalidCategoryNameException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCategoryNameException(InvalidCategoryNameException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(InvalidCategoryDescriptionException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidCategoryDescriptionException(InvalidCategoryDescriptionException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

}