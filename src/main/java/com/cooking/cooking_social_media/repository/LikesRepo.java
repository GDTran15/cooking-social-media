package com.cooking.cooking_social_media.repository;

import com.cooking.cooking_social_media.model.LikeId;
import com.cooking.cooking_social_media.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepo extends JpaRepository<Likes, LikeId> {
    void deleteByLikeIdUserIdAndLikeIdRecipeId(Integer userId, Integer recipeId);

    List<Likes> findAllByLikeIdRecipeId(Integer likeIdRecipeId);
}
