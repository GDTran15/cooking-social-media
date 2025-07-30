package com.cooking.cooking_social_media.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Username is missing")
        String userName,
        @Size(min = 6, message = "Password should have at least 6 character")
        String password,
        @NotBlank(message = "Email is missing")
        String email
) {
}
