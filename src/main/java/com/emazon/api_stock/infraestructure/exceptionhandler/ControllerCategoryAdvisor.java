package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.domain.exception.category.InvalidCategoryDescriptionException;
import com.emazon.api_stock.domain.exception.category.InvalidCategoryNameException;
import com.emazon.api_stock.domain.exception.category.CategoryAlreadyExistsException;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerCategoryAdvisor {


    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                InfraestructureConstants.CATEGORY_ALREADY_EXISTS,
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