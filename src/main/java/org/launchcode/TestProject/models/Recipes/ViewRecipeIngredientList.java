package org.launchcode.TestProject.models.Recipes;

import org.launchcode.TestProject.models.Recipes.Enums.RecipeUOM;
import org.launchcode.TestProject.models.data.recipes.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ViewRecipeIngredientList {
    private String ingredientName;
    private String ingredientAmount;
    private RecipeUOM recipeUOM;

    public ViewRecipeIngredientList(String ingredientName, String ingredientAmount, RecipeUOM recipeUOM) {
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
        this.recipeUOM = recipeUOM;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientAmount() {
        return ingredientAmount;
    }

    public RecipeUOM getRecipeUOM() {
        return recipeUOM;
    }

    public static List<ViewRecipeIngredientList> findRecipeIngredientsByRecipeId(int recipeId, Iterable<RecipeIngredient> recipeIngredientIterable, Iterable<Ingredient> ingredientIterable) {
        List<ViewRecipeIngredientList> recipeIngredients = new ArrayList<ViewRecipeIngredientList>();
        for(RecipeIngredient rec : recipeIngredientIterable) {
            if(rec.getRecipeId() == recipeId) {
                for(Ingredient ing : ingredientIterable) {
                    if(rec.getIngredientId() == ing.getIngredientId()) {
                        recipeIngredients.add(new ViewRecipeIngredientList(ing.getIngredientName(),rec.getIngredientAmount(), rec.getIngredientUOM()));
                    }
                }
            }
        }

        return recipeIngredients;
    }
}
