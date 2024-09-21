package com.emazon.api_stock.infraestructure.exceptionhandler;

import com.emazon.api_stock.domain.exception.PaginationNotAllowedException;
import com.emazon.api_stock.domain.exception.NoDataFoundException;
import com.emazon.api_stock.infraestructure.utils.InfraestructureConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerGeneralAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        StringBuilder messageBuilder = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            messageBuilder.append(errorMessage);
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                messageBuilder.toString().trim(),
                HttpStatus.BAD_REQUEST.toString()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                InfraestructureConstants.NO_DATA_FOUND_EXCEPTION_MESSAGE
                , HttpStatus.NOT_FOUND.toString()));
    }

    @ExceptionHandler(PaginationNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handlePaginationNotAllowedException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                InfraestructureConstants.NEGATIVE_NOT_ALLOWED
                , HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException (AccessDeniedException  exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(
                InfraestructureConstants.ACCESS_DENE,
                HttpStatus.FORBIDDEN.toString()));
    }

}
