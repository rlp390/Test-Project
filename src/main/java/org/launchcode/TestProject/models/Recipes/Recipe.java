package org.launchcode.TestProject.models.Recipes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private int recipeId;

    @OneToMany
    @JoinColumn(name="recipeId")
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="recipeSteps", joinColumns =@JoinColumn(name="recipeId"))
    @Column(name="recipeSteps")
    private List<String> recipeSteps = new ArrayList<>();
}
