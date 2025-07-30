package com.cooking.cooking_social_media.service;

import com.cooking.cooking_social_media.dto.AddRecipeRequestDTO;
import com.cooking.cooking_social_media.dto.RecipeResponseDTO;
import com.cooking.cooking_social_media.dtomapper.RecipeDTOMapper;
import com.cooking.cooking_social_media.exception.ObjectNotFound;
import com.cooking.cooking_social_media.model.Recipe;
import com.cooking.cooking_social_media.model.User;
import com.cooking.cooking_social_media.repository.RecipeRepo;
import com.cooking.cooking_social_media.repository.UserRepo;
import com.cooking.cooking_social_media.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RecipeService {


    private final RecipeRepo recipeRepo;
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final JwtUtil jwtUtil;
    private final RecipeDTOMapper recipeDTOMapper;
    private final UserService userService;

    @Autowired
    public RecipeService(RecipeRepo recipeRepo, UserRepo userRepo, JwtService jwtService, JwtUtil jwtUtil, RecipeDTOMapper recipeDTOMapper, UserService userService) {
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.jwtUtil = jwtUtil;
        this.recipeDTOMapper = recipeDTOMapper;
        this.userService = userService;
    }

    public List<RecipeResponseDTO> getAllRecipe() {
        return recipeRepo.findAll()
                .stream()
                .map(recipeDTOMapper)
                .collect(Collectors.toList());
    }

    public List<RecipeResponseDTO> getAllRecipeWithIngredient(String ingredient) {
        return recipeRepo.findAll().stream()
                .filter(recipe -> recipe.getRecipeIngredients().toLowerCase().contains(ingredient.toLowerCase()))
                .map(recipeDTOMapper)
                .collect(Collectors.toList());

    }
    public List<RecipeResponseDTO> getRecipeFromOwnerName(String userName) {
            Integer userId = userService.findUserByUserName(userName).getUserId();
            return recipeRepo.findAll().stream()
                    .filter(recipe -> Objects.equals(recipe.getUser().getUserId(), userId))
                    .map(recipeDTOMapper)
                    .collect(Collectors.toList());
    }

    @Transactional
    public void updateRecipe(AddRecipeRequestDTO addRecipeRequestDTO, Integer recipeId, String authHeader) {
        Recipe recipe = recipeRepo.findById(recipeId)
                .orElseThrow(() -> new ObjectNotFound("Recipe not found"));
        User user = userRepo.findByUserName(jwtUtil.getNameFromHeader(authHeader));
        if (user == null) {
            throw new ObjectNotFound("User not found");
        }
         recipe.setRecipeName(addRecipeRequestDTO.recipeName());
        recipe.setRecipeIngredients(addRecipeRequestDTO.recipeIngredients());
        recipe.setRecipeDescription(addRecipeRequestDTO.recipeDescription());
        recipeRepo.save(recipe);
    }

    @Transactional
    public void addRecipe(AddRecipeRequestDTO addRecipeRequestDTO, String authHeader) {
            Recipe recipe = new Recipe();
            recipe.setRecipeName(addRecipeRequestDTO.recipeName());
            recipe.setRecipeDescription(addRecipeRequestDTO.recipeDescription());
            recipe.setRecipeIngredients(addRecipeRequestDTO.recipeIngredients());
            recipe.setUser(userRepo.findByUserName(
                    jwtUtil.getNameFromHeader(authHeader)
            ));
            recipeRepo.save(recipe);
    }

    @Transactional
    public void deleteRecipe(Integer recipeId, String authHeader ) {
        String token = authHeader.substring(7);
        String userName = jwtService.extractUserName(token);
        User user = userRepo.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        recipeRepo.deleteById(recipeId);
    }


}
