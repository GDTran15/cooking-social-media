package com.cooking.cooking_social_media.dto;


public record AddRecipeRequestDTO(
                String recipeName,
                String recipeIngredients,
                String recipeDescription
) {
}
