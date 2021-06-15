package org.launchcode.TestProject.controllers;


import org.launchcode.TestProject.models.Recipes.Enums.IngredientType;
import org.launchcode.TestProject.models.Recipes.Ingredient;
import org.launchcode.TestProject.models.data.recipes.IngredientRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeIngredientRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @RequestMapping("")
    private String recipes(Model model) {
        model.addAttribute("title", "El Loggo de Recipio!");
        //model.addAttribute("ingredients", ingredientRepository.findAll());
        //model.addAttribute("recipeIngredients", recipeIngredientRepository.findAll());
        //model.addAttribute("recipes", recipeRepository.findAll());

        return "recipes/index";
    }

    @GetMapping("addIngredient")
    private String addIngredientHome(Model model) {
        model.addAttribute("title", "El Addo de Ingredientio!");
        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("ingredientName", "Enter ingredient name");
        model.addAttribute("ingredientDescription", "Enter ingredient description");
        model.addAttribute("warning","new");

        return "recipes/addIngredient";
    }

    @PostMapping("addIngredient")
    private String processAddIngredient(Model model, @RequestParam IngredientType ingredientType, @RequestParam String ingredientName,
                                        @RequestParam String ingredientDescription) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        // CHECK TO SEE IF INGREDIENT ALREADY EXISTS
        for(Ingredient ingredient : ingredients) {
            System.out.println("CHECK INGREDIENT");
            if(ingredient.getIngredientName().equals(ingredientName)) {

                System.out.println("INGREDIENT ALREADY EXISTS");
                model.addAttribute("title", "El Addo de Ingredientio!");
                model.addAttribute("ingredientTypes", IngredientType.values());
                model.addAttribute("ingredientName", ingredientName);
                model.addAttribute("ingredientDescription", ingredientDescription);
                model.addAttribute("warning","Ingredient already exists!");

                return "/recipes/addIngredient";
            }
        }

        // ADD INGREDIENT IF DOES NOT EXIST YET
        Ingredient newIngredient = new Ingredient(ingredientName, ingredientDescription, ingredientType);
        ingredientRepository.save(newIngredient);

        model.addAttribute("title", "El Addo de Ingredientio!");
        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("ingredientName", "Enter ingredient name");
        model.addAttribute("ingredientDescription", "Enter ingredient description");
        model.addAttribute("warning","Ingredient added!  Enter another Ingredient?");

        return "/recipes/addIngredient";
    }
}
