package com.cooking.cooking_social_media.service;

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
    public User createUser(User user) {
        User userNameExist = userRepo.findByUserName(user.getUserName());
        User userEmailExist = userRepo.findByEmail(user.getEmail());
        if (userNameExist != null) {
            throw new RuntimeException("Username already exists");
        }
        if ( userEmailExist != null) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepo.save(user);
        
    }
}
