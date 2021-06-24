package org.launchcode.TestProject.controllers;


import org.launchcode.TestProject.models.Recipes.Enums.IngredientType;
import org.launchcode.TestProject.models.Recipes.Ingredient;
import org.launchcode.TestProject.models.Recipes.Recipe;
import org.launchcode.TestProject.models.data.recipes.IngredientRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeIngredientRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeStepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeStepsRepository recipeStepsRepository;

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

    @GetMapping("editIngredient")
    public String editIngredientHome(Model model) {
        model.addAttribute("title", "El Edito de Ingredientio!");
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "/recipes/editIngredient";
    }

    @PostMapping("editIngredient")
    public String processEditIngredient(Model model, @RequestParam int ingredientId) {
        Optional<Ingredient> option = ingredientRepository.findById(ingredientId);
        Ingredient ingredient = (Ingredient) option.get();

        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("title","El Updatio de Ingredientio!");

        return "/recipes/updateIngredient";
    }

    @PostMapping("updateIngredient")
    public String processUpdateIngredient(Model model, @RequestParam String ingredientName, @RequestParam String ingredientDescription, @RequestParam IngredientType ingredientType, @RequestParam int ingredientId) {
        Optional<Ingredient> option = ingredientRepository.findById(ingredientId);
        Ingredient ingredient = (Ingredient) option.get();

        ingredient.setIngredientName(ingredientName);
        ingredient.setIngredientType(ingredientType);
        ingredient.setIngredientDescription(ingredientDescription);

        ingredientRepository.save(ingredient);

        model.addAttribute("title", "El Edito de Ingredientio!");
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "redirect:../recipes/editIngredient";
    }

    @GetMapping("deleteIngredient/{id}")
    public String processDeleteIngredient(Model model, @PathVariable int id) {
        ingredientRepository.deleteById(id);

        model.addAttribute("title", "El Edito de Ingredientio!");
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "redirect:/recipes/editIngredient";
    }

    @GetMapping("create")
    public String createRecipeHome(Model model) {
        model.addAttribute("title","El Creatio de New Recipio!");
        model.addAttribute("recipeName", "Enter recipe name");
        model.addAttribute("recipeDesc", "Enter recipe description");

        return "/recipes/createRecipe";
    }

    @PostMapping("create")
    public String processCreateRecipe(Model model, @RequestParam String recipeName, @RequestParam String recipeDesc) {
        Recipe recipe = new Recipe(recipeName, recipeDesc);
        recipeRepository.save(recipe);

        model.addAttribute("title", "El Recipio de Addo Ingredientio!");
        model.addAttribute("recipe", recipe);

        return "/recipes/addRecipeIngredients";
    }
}
