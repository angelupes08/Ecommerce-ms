package com.categoryservices.exception;

import com.categoryservices.payload.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex){

        ErrorObject err = new ErrorObject();

        err.setMessage(ex.getMessage());
        err.setTimeStamp(new Date());
        err.setStatusCode(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorObject> handleUnauthorizedException(UnauthorizedException ex){

        ErrorObject err = new ErrorObject();

        err.setMessage(ex.getMessage());
        err.setTimeStamp(new Date());
        err.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(err,HttpStatus.UNAUTHORIZED);
    }
}
