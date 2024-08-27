package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.infraestructure.exception.NegativeNotAllowedException;
import com.emazon.api_stock.infraestructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGeneralAdvisor {

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                ExceptionResponseConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE.getMessage()
                , HttpStatus.NOT_FOUND.toString()));
    }

    @ExceptionHandler(NegativeNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleNegativeNotAllowedException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                ExceptionResponseConstants.NEGATIVE_NOT_ALLOWED.getMessage()
                , HttpStatus.BAD_REQUEST.toString()));
    }
}
