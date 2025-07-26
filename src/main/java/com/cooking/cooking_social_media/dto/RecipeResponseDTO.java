package com.cooking.cooking_social_media.dto;

public record RecipeResponseDTO(
        String recipeName,
        String recipeIngredients,
        String recipeDescription,
        String userName
) {

}
