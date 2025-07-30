package com.cooking.cooking_social_media.exception;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler { // make this for exception
    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<String> objectNotFound(ObjectNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameOrEmailAlreadyExist.class)
    public ResponseEntity<String> userNameOrEmailAlreadyExist(UserNameOrEmailAlreadyExist ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValid(MethodArgumentNotValidException ex){
        Map<String,String> error = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        error.put(fieldError.getField(),fieldError.getDefaultMessage()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
