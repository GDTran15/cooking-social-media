package com.cooking.cooking_social_media.dtomapper;

import com.cooking.cooking_social_media.dto.RecipeResponseDTO;
import com.cooking.cooking_social_media.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecipeDTOMapper implements Function<Recipe, RecipeResponseDTO> {


    @Override
    public RecipeResponseDTO apply(Recipe recipe) {
        return new RecipeResponseDTO(
                recipe.getRecipeName(),
                recipe.getRecipeIngredients(),
                recipe.getRecipeDescription(),
                recipe.getUser().getUserName());
    }
}
