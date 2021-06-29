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

    @OneToMany
    @JoinColumn(name="recipeId")
    private List<RecipeSteps> recipeSteps = new ArrayList<>();

    private String recipeName;

    private String recipeDescription;

    public Recipe(String recipeName, String recipeDescription) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public Recipe() {}

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public int getRecipeId() { return recipeId;  }
}
