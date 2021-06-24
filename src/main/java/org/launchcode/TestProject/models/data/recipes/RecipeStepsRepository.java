package org.launchcode.TestProject.models.data.recipes;

import org.launchcode.TestProject.models.Recipes.RecipeSteps;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStepsRepository extends CrudRepository<RecipeSteps, Integer> {
}
