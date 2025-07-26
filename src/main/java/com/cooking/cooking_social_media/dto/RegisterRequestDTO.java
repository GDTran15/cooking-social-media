package com.cooking.cooking_social_media.dto;

public record RegisterRequestDTO(
        String userName,
        String password,
        String email
) {
}
