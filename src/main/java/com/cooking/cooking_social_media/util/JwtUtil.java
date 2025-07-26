package com.cooking.cooking_social_media.util;

import com.cooking.cooking_social_media.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Autowired
    private JwtService jwtService;

    public String getNameFromHeader(String authHeader) {
        String token = authHeader.substring(7);
        return jwtService.extractUserName(token);
    }

}
