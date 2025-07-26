package com.cooking.cooking_social_media.service;

import com.cooking.cooking_social_media.dto.RegisterRequestDTO;
import com.cooking.cooking_social_media.model.User;
import com.cooking.cooking_social_media.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepo userRepo;

    @Transactional(rollbackFor = Exception.class)
    public User createUser(RegisterRequestDTO registerForm) {
        User userNameExist = userRepo.findByUserName(registerForm.userName());
        User userEmailExist = userRepo.findByEmail(registerForm.email());
        if (userNameExist != null) {
            throw new RuntimeException("Username already exists");
        }
        if ( userEmailExist != null) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUserName(registerForm.userName());
        user.setEmail(registerForm.email());
        user.setPassword(bCryptPasswordEncoder.encode(registerForm.password()));

        return userRepo.save(user);
        
    }

    public User findUserByUserName(String userName) {
        return userRepo.findByUserName(userName);
    }
}
