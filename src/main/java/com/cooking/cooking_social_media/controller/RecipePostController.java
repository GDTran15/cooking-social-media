package com.cooking.cooking_social_media.controller;

import com.cooking.cooking_social_media.dto.AddRecipeRequestDTO;
import com.cooking.cooking_social_media.dto.RecipeResponseDTO;
import com.cooking.cooking_social_media.dtomapper.RecipeDTOMapper;
import com.cooking.cooking_social_media.model.Recipe;
import com.cooking.cooking_social_media.service.JwtService;
import com.cooking.cooking_social_media.service.RecipeService;
import com.cooking.cooking_social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class RecipePostController {

    private final RecipeService recipeService;

   @Autowired
    public RecipePostController(RecipeService recipeService) {
        this.recipeService = recipeService;

    }

    @GetMapping("recipe")
    public ResponseEntity<List<RecipeResponseDTO>> findAllRecipes() {
         return new ResponseEntity<>( recipeService.getAllRecipe(), HttpStatus.OK);
    }

    @GetMapping("recipe/{ingredient}")
    public ResponseEntity<List<RecipeResponseDTO>> findRecipeByIngredient(@PathVariable String ingredient) {
        return new ResponseEntity<>(recipeService.getAllRecipeWithIngredient(ingredient), HttpStatus.OK );
    }

    @GetMapping("recipe/user/{userName}")
    public ResponseEntity<List<RecipeResponseDTO>> findRecipeByUserId(@PathVariable String userName) {
       return new ResponseEntity<>(recipeService.getRecipeFromOwnerName(userName), HttpStatus.OK);
    }

    @PostMapping("recipe")
    public ResponseEntity<String> addRecipe(@RequestBody AddRecipeRequestDTO addRecipeRequestDTO, @RequestHeader("Authorization") String authHeader) {
        recipeService.addRecipe(addRecipeRequestDTO, authHeader);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @PutMapping("recipe/{recipeId}")
    public ResponseEntity<String> updateRecipe(@RequestBody AddRecipeRequestDTO addRecipeRequestDTO, @PathVariable Integer recipeId,@RequestHeader("Authorization") String authHeader) {
        recipeService.updateRecipe(addRecipeRequestDTO,recipeId, authHeader);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("recipe/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Integer recipeId, @RequestHeader("Authorization") String authHeader) {
        recipeService.deleteRecipe(recipeId, authHeader);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }



}
