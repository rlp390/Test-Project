package org.launchcode.TestProject.models.Recipes;

import org.launchcode.TestProject.models.Recipes.Enums.IngredientType;
import org.launchcode.TestProject.models.Recipes.Enums.RecipeUOM;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue
    private int recipeIngredientId;

    private int recipeId;

    private int ingredientId;

    private String ingredientAmount;

    private RecipeUOM ingredientUOM;

    public RecipeIngredient(int recipeId, int ingredientId, String ingredientAmount, RecipeUOM ingredientUOM) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientAmount = ingredientAmount;
        this.ingredientUOM = ingredientUOM;
    }

    public RecipeIngredient() {    }

    public int getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientAmount() {
        return ingredientAmount;
    }

    public RecipeUOM getIngredientUOM() {
        return ingredientUOM;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientAmount(String ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    public void setIngredientUOM(RecipeUOM ingredientUOM) {
        this.ingredientUOM = ingredientUOM;
    }


}
