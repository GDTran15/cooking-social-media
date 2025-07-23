package com.cooking.cooking_social_media.controller;

import com.cooking.cooking_social_media.model.User;
import com.cooking.cooking_social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccessController {

    private UserService service;

    @Autowired
    public UserAccessController(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try{
            service.createUser(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (RuntimeException e){}
        return new ResponseEntity<>("Username or email exist", HttpStatus.CONFLICT);
    }



}
