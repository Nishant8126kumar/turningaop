package com.example.aopdemo.exceptions;

import com.example.aopdemo.utils.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(value = NotAllowedException.class)
    public ResponseEntity<?> handleNotAllowedException(NotAllowedException notAllowedException) {
        return ResponseEntity.ok(AppResponse.makeErrorResponse(notAllowedException.getMessage()));
    }

    @ExceptionHandler(value = MongoDbException.class)
    public ResponseEntity<?> handleDbException(MongoDbException mongoDbException) {
        return ResponseEntity.ok(AppResponse.makeErrorResponse(mongoDbException.getMessage()));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ResponseEntity.ok(AppResponse.makeResponse(resourceNotFoundException.getMessage()));
    }
}
