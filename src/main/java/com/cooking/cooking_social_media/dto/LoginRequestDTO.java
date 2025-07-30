package com.cooking.cooking_social_media.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank (message = "Username missing")
        String userName,
        @NotBlank (message = "Password missing")
        String password) {
}
