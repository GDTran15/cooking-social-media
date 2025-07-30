package com.cooking.cooking_social_media.controller;

import com.cooking.cooking_social_media.dto.CommentsDTOResponse;
import com.cooking.cooking_social_media.dto.CommentsRequestDTO;
import com.cooking.cooking_social_media.model.CommentId;
import com.cooking.cooking_social_media.model.Comments;
import com.cooking.cooking_social_media.model.Recipe;
import com.cooking.cooking_social_media.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("recipe/comment")
public class CommentController {

    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public ResponseEntity<String> addComment(@RequestBody CommentsRequestDTO commentsRequestDTO, @RequestHeader("Authorization") String authHeader) {
        commentService.createComment(commentsRequestDTO,authHeader);
        return new ResponseEntity<>("Comment created", HttpStatus.CREATED);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteComment(@RequestBody Integer recipeId, @RequestHeader("Authorization") String authHeader) {
        commentService.deleteComments(recipeId,authHeader);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);

    }

    @PutMapping()
    public ResponseEntity<String> updateComment(@RequestBody CommentsRequestDTO commentsRequestDTO, @RequestHeader("Authorization") String authHeader) {
        commentService.createComment(commentsRequestDTO, authHeader);
        return new ResponseEntity<>("Comment updated", HttpStatus.ACCEPTED);
    }
    
    @GetMapping()
    public ResponseEntity<List<CommentsDTOResponse>> getComments(@RequestBody Integer recipeId) {
        return new ResponseEntity<>(commentService.getAllComments(recipeId), HttpStatus.OK);
    }
}
