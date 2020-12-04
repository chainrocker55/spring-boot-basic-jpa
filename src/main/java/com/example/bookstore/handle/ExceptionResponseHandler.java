package com.example.bookstore.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionResponseHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Exception> accountNotFound(
            RuntimeException exception
    ) {
        Exception error = new Exception();
        error.setCode(404);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<Exception>(error, HttpStatus.OK);
    }
}
