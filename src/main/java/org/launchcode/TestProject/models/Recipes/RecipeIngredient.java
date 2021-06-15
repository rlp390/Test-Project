package org.launchcode.TestProject.models.Recipes;

import org.launchcode.TestProject.models.Recipes.Enums.IngredientType;
import org.launchcode.TestProject.models.Recipes.Enums.RecipeUOM;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue
    private int recipeIngredientId;

    private int recipeId;

    private int ingredientId;

    private String ingredientAmount;

    private RecipeUOM ingredientUOM;
}
