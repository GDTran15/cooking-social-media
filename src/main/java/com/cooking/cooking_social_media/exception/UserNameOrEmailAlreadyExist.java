package com.cooking.cooking_social_media.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserNameOrEmailAlreadyExist extends RuntimeException {
    public UserNameOrEmailAlreadyExist(String message) {
        super(message);
    }
}
