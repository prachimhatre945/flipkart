package com.example.flipkart.ExceptionHandeling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception Ex){
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp" , LocalDateTime.now());
        map.put("errorMsg" , Ex.getMessage());
        map.put("statusCode" , HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("error" , "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleResourceNotFoundException(CustomException ex){
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp" , LocalDateTime.now());
        map.put("errorMsg" , ex.getMessage());
        map.put("statusCode" , HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("error" , "Internal Server Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
