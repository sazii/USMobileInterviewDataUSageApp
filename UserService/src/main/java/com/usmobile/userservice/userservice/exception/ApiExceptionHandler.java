package com.usmobile.userservice.userservice.exception;

import com.usmobile.shared.model.exception.AbstractApiException;
import com.usmobile.shared.model.exception.ExceptionError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExceptionError> handle(Exception ex){
        return ResponseEntity.internalServerError()
                .body(ExceptionError
                        .builder()
                        .code(ex instanceof AbstractApiException ? ((AbstractApiException) ex).getCode() : 500)
                        .message(ex.getMessage())
                .build());
    }
}
