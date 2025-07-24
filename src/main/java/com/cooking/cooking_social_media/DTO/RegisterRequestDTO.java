package com.cooking.cooking_social_media.DTO;

public record RegisterRequestDTO(
        String userName,
        String password,
        String email
) {
}
