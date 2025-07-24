package com.cooking.cooking_social_media.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CommentId implements Serializable {
    private Integer userId;
    private Integer recipeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                 // (1)
        if (!(o instanceof CommentId commentId)) return false;          // (2)
        return userId == commentId.userId && recipeId == commentId.recipeId; // (3)
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, recipeId);
    }
}
