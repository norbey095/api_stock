package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.domain.exception.brand.BrandAlreadyExistsException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandDescriptionException;
import com.emazon.api_stock.domain.exception.brand.InvalidBrandNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerBrandAdvisor {


    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleBrandAlreadyExistsException(BrandAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                ExceptionResponseConstants.BRAND_ALREADY_EXISTS.getMessage(),
                HttpStatus.CONFLICT.toString()));
    }

    @ExceptionHandler(InvalidBrandNameException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidBrandNameException(InvalidBrandNameException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(InvalidBrandDescriptionException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidBrandDescriptionException(InvalidBrandDescriptionException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.toString()));
    }
}