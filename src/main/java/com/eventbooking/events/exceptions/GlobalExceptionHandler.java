package com.eventbooking.events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionApiResponse> userException(UserException userException){
        return new ResponseEntity<>(ExceptionApiResponse.builder()
                .data(userException.getMessage())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventExistException.class)
    public ResponseEntity<ExceptionApiResponse> eventExist(EventExistException existException){
        return new ResponseEntity<>(ExceptionApiResponse.builder()
                .data(existException.getMessage())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
