package com.cooking.cooking_social_media.dto;

public record CommentsRequestDTO(
        Integer recipeId,
        String comments
) {
}
