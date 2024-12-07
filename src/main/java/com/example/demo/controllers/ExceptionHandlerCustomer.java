package com.example.demo.controllers;

import com.mongodb.MongoQueryException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerCustomer {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);

    }
    @ExceptionHandler(MongoQueryException.class)
    public ResponseEntity<Map> handleMongoQueryException(MongoQueryException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return ResponseEntity.internalServerError().body(errors);
    }
}
