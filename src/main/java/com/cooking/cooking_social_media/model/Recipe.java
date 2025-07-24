package com.cooking.cooking_social_media.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    private String recipeName;
    private String recipeIngredients;
    private String recipeDescription;

    @OneToMany(mappedBy = "recipe")
    private List<Likes> likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
