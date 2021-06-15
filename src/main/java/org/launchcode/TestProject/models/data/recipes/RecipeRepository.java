package org.launchcode.TestProject.models.data.recipes;

import org.launchcode.TestProject.models.Recipes.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
}
