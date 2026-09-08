package com.enterprise.chat.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(
            ResourceNotFoundException ex
    ){
        Map<String,Object> errorResponse=new LinkedHashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","Not Found");
        errorResponse.put("message",ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(errorResponse);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>> handleResourceAlreadyExists(
            ResourceAlreadyExistsException ex
    ){
        Map<String,Object> errorResponse=new LinkedHashMap<>();
        errorResponse.put("timestamp",LocalDateTime.now());
        errorResponse.put("status",HttpStatus.CONFLICT.value());
        errorResponse.put("error","Conflict");
        errorResponse.put("message",ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ){
        Map<String,String> validationError=new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error->
                        validationError.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));
        Map<String,Object> errorResponse=new LinkedHashMap<>();
        errorResponse.put("timestamp",LocalDateTime.now());
        errorResponse.put("status",HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error","Bad Request");
        errorResponse.put("message","Validation failed");
        errorResponse.put("errors",validationError);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

}
