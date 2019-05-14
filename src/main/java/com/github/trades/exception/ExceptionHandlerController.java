package com.github.trades.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> badRequest(
            Exception ex) {
        return new ResponseEntity<>("Query failed, " + ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

}