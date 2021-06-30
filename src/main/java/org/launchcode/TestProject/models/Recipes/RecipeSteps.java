package org.launchcode.TestProject.models.Recipes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class RecipeSteps {

    @Id
    @GeneratedValue
    private int id;

    private int recipeId;

    @NotBlank
    private String stepDirections;

    public RecipeSteps(int recipeId, String stepDirections) {
        this.recipeId = recipeId;
        this.stepDirections = stepDirections;
    }

    public RecipeSteps() {}

    public int getId() {
        return id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getStepDirections() {
        return stepDirections;
    }

    public void setStepDirections(String stepDirections) {
        this.stepDirections = stepDirections;
    }
}
