package com.cooking.cooking_social_media.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LikeId implements Serializable {
    private Integer userId;
    private Integer recipeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                                 // (1)
        if (!(o instanceof LikeId likeId)) return false;          // (2)
        return userId == likeId.userId && recipeId == likeId.recipeId; // (3)
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, recipeId);
    }
}

