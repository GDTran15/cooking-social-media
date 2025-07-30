package com.cooking.cooking_social_media.repository;

import com.cooking.cooking_social_media.model.CommentId;
import com.cooking.cooking_social_media.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, CommentId> {
    List<Comments> findAllByCommentsIdRecipeId(Integer recipeId);
}
