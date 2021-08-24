package com.example.aopdemo.exceptions;

import com.example.aopdemo.utils.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(value = NotAllowedException.class)
    public ResponseEntity<?> handleNotAllowedException(NotAllowedException notAllowedException) {
        return ResponseEntity.ok(new AppResponse( null,500, notAllowedException.getMessage()));
    }

    @ExceptionHandler(value = MongoDbException.class)
    public ResponseEntity<?> handleDbException(MongoDbException mongoDbException) {
        return ResponseEntity.ok(new AppResponse( null,500, mongoDbException.getMessage()));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseEntity.ok(new AppResponse( null,500, resourceNotFoundException.getMessage()));
    }
}
