package com.example.aopdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(value = NotAllowedException.class)
    public ResponseEntity<?> handleNotAllowedException(NotAllowedException notAllowedException) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", null);
        response.put("message", notAllowedException.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(value = DBException.class)
    public ResponseEntity<?> handleDbException(DBException dbException) {
        System.out.println("Exception come here");
        Map<String, Object> response = new HashMap<>();
        response.put("data", null);
        response.put("message", dbException.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response);
    }
}
