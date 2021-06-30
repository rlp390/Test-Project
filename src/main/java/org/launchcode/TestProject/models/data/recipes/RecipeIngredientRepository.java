package org.launchcode.TestProject.models.data.recipes;

import org.launchcode.TestProject.models.Recipes.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
    Iterable<RecipeIngredient> findByRecipeId(int recipeId);
}
