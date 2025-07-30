package com.cooking.cooking_social_media.service;

import com.cooking.cooking_social_media.dto.LikeDTOResponse;
import com.cooking.cooking_social_media.dtomapper.LikeDTOMapper;
import com.cooking.cooking_social_media.exception.ObjectNotFound;
import com.cooking.cooking_social_media.model.LikeId;
import com.cooking.cooking_social_media.model.Likes;
import com.cooking.cooking_social_media.model.Recipe;
import com.cooking.cooking_social_media.model.User;
import com.cooking.cooking_social_media.repository.LikesRepo;
import com.cooking.cooking_social_media.repository.RecipeRepo;
import com.cooking.cooking_social_media.repository.UserRepo;
import com.cooking.cooking_social_media.util.JwtUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private final  JwtUtil jwtUtil;
    private final RecipeRepo recipeRepo;
    private final UserRepo userRepo;
    private final LikesRepo likesRepo;
    private final LikeDTOMapper likeDTOMapper;

    public LikeService(JwtUtil jwtUtil, RecipeRepo recipeRepo, UserRepo userRepo, LikesRepo likesRepo , LikeDTOMapper likeDTOMapper) {
        this.jwtUtil = jwtUtil;
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
        this.likesRepo = likesRepo;
        this.likeDTOMapper = likeDTOMapper;
    }

    public void addLikeToRecipe(Integer recipeId, String authHeader) {
            Likes like = new Likes();
            Recipe recipe  = recipeRepo.findById(recipeId).orElseThrow(() -> new ObjectNotFound("Recipe not found"));
            User user = userRepo.findByUserName(jwtUtil.getNameFromHeader(authHeader));
            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            LikeId likeId = new LikeId(recipeId, user.getUserId());

            like.setLikeId(likeId);
            like.setUser(user);
            like.setRecipe(recipe);
            likesRepo.save(like);

    }

    public void unlikeTheRecipe(Integer recipeId, String authHeader) {
        User user = userRepo.findByUserName(jwtUtil.getNameFromHeader(authHeader));
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        LikeId likeId = new LikeId( user.getUserId(), recipeId);
        likesRepo.deleteById(likeId);
    }


    public List<LikeDTOResponse> getLikesOfRecipe(Integer recipeId) {
        return likesRepo.findAllByLikeIdRecipeId(recipeId).stream()
                .map(likeDTOMapper)
                .collect(Collectors.toList());
    }
}
