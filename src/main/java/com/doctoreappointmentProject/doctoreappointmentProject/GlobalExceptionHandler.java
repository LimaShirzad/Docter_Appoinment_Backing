package com.doctoreappointmentProject.doctoreappointmentProject;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //  Field validation errors (@NotBlank, @Size, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "errors", errors
        ));
    }

    //  Service or manual errors (your IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleServiceErrors(IllegalArgumentException ex) {
        //  just use the message thrown in your service
        return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", ex.getMessage()
        ));
    }

    //  Entity-level exceptions (@PrePersist / @PreUpdate)
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, String>> handleTransactionErrors(TransactionSystemException ex) {
        Throwable cause = ex.getRootCause(); // get original cause
        if (cause instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", cause.getMessage() // show message from entity preSave()
            ));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", "error",
                "message", "Something went wrong: " + ex.getMessage()
        ));
    }

    //  Catch-all for unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllOtherErrors(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", "error",
                "message",  ex.getMessage()
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeExceptions(RuntimeException ex) {
        return ResponseEntity.badRequest().body(Map.of(
                "status", "error",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String,String>> handleDataIntegrityViolation(DataIntegrityViolationException ex){

        Map<String,String> error=new HashMap<>();

        error.put("error","You can Not Delete This Record Because It is Referenced in another table!");

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public  ResponseEntity<?> handleBadCredentials(BadCredentialsException ex){

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("BadCredential","Invalid UserName and Password"));

    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> handleRuntime(RuntimeException ex){
//
//        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error","somthing went wrong","details",ex.getMessage()));
//
//    }





}

