package org.launchcode.TestProject.models.Recipes;

import org.launchcode.TestProject.models.Recipes.Enums.IngredientType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient {

    public Ingredient(String ingredientName, String ingredientDescription, IngredientType ingredientType) {
        this.ingredientName = ingredientName;
        this.ingredientDescription = ingredientDescription;
        this.ingredientType = ingredientType;
    }

    public Ingredient() {}

    @Id
    @GeneratedValue
    private int ingredientId;

    private String ingredientName;

    private String ingredientDescription;

    private IngredientType ingredientType;

    public int getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }
}
