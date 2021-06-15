package org.launchcode.TestProject.models.data.recipes;

import org.launchcode.TestProject.models.Recipes.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
