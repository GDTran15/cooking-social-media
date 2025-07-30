package com.cooking.cooking_social_media.controller;


import com.cooking.cooking_social_media.dto.LikeDTOResponse;
import com.cooking.cooking_social_media.model.Likes;
import com.cooking.cooking_social_media.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recipe/like")
public class LikeController {

    LikeService likeService;

    @Autowired
    LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping()
    public ResponseEntity<String> likeForRecipe(@RequestParam Integer recipeId, @RequestHeader("Authorization") String authHeader) {
        likeService.addLikeToRecipe(recipeId,authHeader);
        return new ResponseEntity<>("Successfully added like to recipe", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> unlikeForRecipe(@RequestParam Integer recipeId, @RequestHeader("Authorization") String authHeader) {
        likeService.unlikeTheRecipe(recipeId,authHeader);
        return new ResponseEntity<>("Successfully removed like from recipe", HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<LikeDTOResponse>> getLikes(@RequestParam Integer recipeId) {
        return new ResponseEntity<>(likeService.getLikesOfRecipe(recipeId), HttpStatus.OK);
        }
    }

