package com.cooking.cooking_social_media.controller;

import com.cooking.cooking_social_media.dto.LoginRequestDTO;
import com.cooking.cooking_social_media.dto.LoginResponseDTO;
import com.cooking.cooking_social_media.dto.RegisterRequestDTO;
import com.cooking.cooking_social_media.model.User;
import com.cooking.cooking_social_media.repository.UserRepo;
import com.cooking.cooking_social_media.service.JwtService;
import com.cooking.cooking_social_media.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccessController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    public UserAccessController(UserService service) {
        this.service = service;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
            service.createUser(registerRequestDTO);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.userName(), loginRequest.password()));
        if (authentication.isAuthenticated()) {
            User user = userRepo.findByUserName(loginRequest.userName());
             String token  = jwtService.generateToken(user);
             return new ResponseEntity<>( new LoginResponseDTO(token, user.getUserId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }


}
