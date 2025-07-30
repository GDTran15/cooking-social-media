package com.cooking.cooking_social_media.service;

import com.cooking.cooking_social_media.dto.CommentsDTOResponse;
import com.cooking.cooking_social_media.dto.CommentsRequestDTO;
import com.cooking.cooking_social_media.exception.ObjectNotFound;
import com.cooking.cooking_social_media.model.CommentId;
import com.cooking.cooking_social_media.model.Comments;
import com.cooking.cooking_social_media.model.Recipe;
import com.cooking.cooking_social_media.model.User;
import com.cooking.cooking_social_media.repository.CommentsRepo;
import com.cooking.cooking_social_media.repository.RecipeRepo;
import com.cooking.cooking_social_media.repository.UserRepo;
import com.cooking.cooking_social_media.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentsRepo commentsRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RecipeRepo recipeRepo;

    @Autowired
    private JwtUtil jwtUtil;


    public void createComment(CommentsRequestDTO commentsRequestDTO, String authHeader) {
        Comments comments = new Comments();
        String userName = jwtUtil.getNameFromHeader(authHeader);
        User user = userRepo.findByUserName(userName);
        Recipe recipe = recipeRepo.findById(commentsRequestDTO.recipeId())
                .orElseThrow(() -> new ObjectNotFound("Recipe not found"));
        CommentId commentId = new CommentId(user.getUserId(), commentsRequestDTO.recipeId());
        comments.setCommentId(commentId);
        comments.setRecipe(recipe);
        comments.setUser(user);
        comments.setComment(commentsRequestDTO.comments());
        commentsRepo.save(comments);

    }

    public void deleteComments(Integer recipeId, String authHeader) {
        String userName = jwtUtil.getNameFromHeader(authHeader);
        User user = userRepo.findByUserName(userName);
        CommentId commentId = new CommentId(user.getUserId(), recipeId);
        commentsRepo.deleteById(commentId);
    }


    public List<CommentsDTOResponse> getAllComments(Integer recipeId) {
       return commentsRepo.findAllByCommentsIdRecipeId(recipeId).stream()
                .map(comments -> new CommentsDTOResponse(
                        comments.getComment()
                ))
               .collect(Collectors.toList());
    }
}
