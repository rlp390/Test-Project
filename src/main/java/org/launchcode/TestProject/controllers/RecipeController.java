package org.launchcode.TestProject.controllers;


import org.launchcode.TestProject.models.Recipes.*;
import org.launchcode.TestProject.models.Recipes.Enums.IngredientType;
import org.launchcode.TestProject.models.Recipes.Enums.RecipeUOM;
import org.launchcode.TestProject.models.data.recipes.IngredientRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeIngredientRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeRepository;
import org.launchcode.TestProject.models.data.recipes.RecipeStepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("recipes")
public class RecipeController extends AbstractController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeStepsRepository recipeStepsRepository;

    @RequestMapping("")
    private String recipes(Model model, HttpServletRequest request) {
        model.addAttribute("title", "El Loggo de Recipio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("recipes", recipeRepository.findAll());

        return "recipes/index";
    }

    @GetMapping("addIngredient")
    private String addIngredientHome(Model model, HttpServletRequest request) {
        model.addAttribute("title", "El Addo de Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("ingredientName", "Enter ingredient name");
        model.addAttribute("ingredientDescription", "Enter ingredient description");
        model.addAttribute("warning","new");

        return "recipes/addIngredient";
    }

    @PostMapping("addIngredient")
    private String processAddIngredient(Model model, @RequestParam IngredientType ingredientType, @RequestParam String ingredientName,
                                        @RequestParam String ingredientDescription, HttpServletRequest request) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        // CHECK TO SEE IF INGREDIENT ALREADY EXISTS
        for(Ingredient ingredient : ingredients) {
            if(ingredient.getIngredientName().equals(ingredientName)) {

                // INGREDIENT ALREADY EXISTS

                model.addAttribute("title", "El Addo de Ingredientio!");
                model.addAttribute("username", returnLoginName(request));
                model.addAttribute("login", returnLoginURL(request));
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
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("ingredientName", "Enter ingredient name");
        model.addAttribute("ingredientDescription", "Enter ingredient description");
        model.addAttribute("warning","Ingredient added!  Enter another Ingredient?");

        return "/recipes/addIngredient";
    }

    @GetMapping("editIngredient")
    public String editIngredientHome(Model model, HttpServletRequest request) {
        List<Ingredient> ingredients = Ingredient.sortIngredientsList(ingredientRepository.findAll());

        model.addAttribute("title", "El Edito de Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("ingredients", ingredients);

        return "/recipes/editIngredient";
    }

    @PostMapping("editIngredient")
    public String processEditIngredient(Model model, @RequestParam int ingredientId, HttpServletRequest request) {
        Optional<Ingredient> option = ingredientRepository.findById(ingredientId);
        Ingredient ingredient = (Ingredient) option.get();

        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("title","El Updatio de Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));

        return "/recipes/updateIngredient";
    }

    @PostMapping("updateIngredient")
    public String processUpdateIngredient(Model model, @RequestParam String ingredientName, @RequestParam String ingredientDescription,
                                          @RequestParam IngredientType ingredientType, @RequestParam int ingredientId, HttpServletRequest request) {
        Optional<Ingredient> option = ingredientRepository.findById(ingredientId);
        Ingredient ingredient = (Ingredient) option.get();

        ingredient.setIngredientName(ingredientName);
        ingredient.setIngredientType(ingredientType);
        ingredient.setIngredientDescription(ingredientDescription);

        ingredientRepository.save(ingredient);

        model.addAttribute("title", "El Edito de Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "redirect:../recipes/editIngredient";
    }

    @GetMapping("deleteIngredient/{id}")
    public String processDeleteIngredient(Model model, @PathVariable int id, HttpServletRequest request) {
        ingredientRepository.deleteById(id);

        model.addAttribute("title", "El Edito de Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "redirect:/recipes/editIngredient";
    }

    @GetMapping("create")
    public String createRecipeHome(Model model, HttpServletRequest request) {
        model.addAttribute("title","El Creatio de New Recipio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("recipeName", "Enter recipe name");
        model.addAttribute("recipeDesc", "Enter recipe description");

        return "/recipes/createRecipe";
    }

    @PostMapping("create")
    public String processCreateRecipe(Model model, @RequestParam String recipeName, @RequestParam String recipeDesc, HttpServletRequest request) {
        Recipe recipe = new Recipe(recipeName, recipeDesc);
        recipeRepository.save(recipe);

        return "redirect:/recipes/RecipeIngredients/" + recipe.getRecipeId();
    }

    @GetMapping("RecipeIngredients/{recipeId}")
    public String recipeIngredientHome(Model model, @PathVariable int recipeId, HttpServletRequest request) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optionalRecipe.get();

        List<Ingredient> ingredients = Ingredient.sortIngredientsList(ingredientRepository.findAll());

        List<ViewRecipeIngredientList> recipeIngredients = ViewRecipeIngredientList.findRecipeIngredientsByRecipeId(recipeId, recipeIngredientRepository.findAll(),
                ingredientRepository.findAll());

        model.addAttribute("title", "El Recipio de Addo Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("recipe", recipe);
        model.addAttribute("UOM", RecipeUOM.values());
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("recipeIngredients",recipeIngredients);
        model.addAttribute("recipeSteps", recipeStepsRepository.findByRecipeId(recipeId));

        return "/recipes/RecipeIngredients";
    }

    @PostMapping("RecipeIngredients/{recipeId}")
    public String processAddRecipeIngredient(@PathVariable int recipeId, @RequestParam(required = false) Float ingredientAmount, @RequestParam RecipeUOM ingredientUOM,
                                             @RequestParam int ingredientId) {

        if(ingredientAmount == null) return "redirect:/recipes/RecipeIngredients/" + recipeId;

        RecipeIngredient recipeIngredient = new RecipeIngredient(recipeId, ingredientId, ingredientAmount.toString(), ingredientUOM);
        recipeIngredientRepository.save(recipeIngredient);

        return "redirect:/recipes/RecipeIngredients/" + recipeId;
    }

    @GetMapping("viewRecipe/{recipeId}")
    public String viewRecipeHome(@PathVariable int recipeId, Model model,HttpServletRequest request) {

        Optional optionalRecipe = recipeRepository.findById(recipeId);
        Recipe recipe = (Recipe) optionalRecipe.get();

        List<ViewRecipeIngredientList> recipeIngredients = ViewRecipeIngredientList.findRecipeIngredientsByRecipeId(recipeId, recipeIngredientRepository.findByRecipeId(recipeId),
                ingredientRepository.findAll());


        model.addAttribute("title", "El Recipio de Addo Ingredientio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeIngredients",recipeIngredients);
        model.addAttribute("recipeSteps", recipeStepsRepository.findByRecipeId(recipeId));

        return "/recipes/viewRecipe";
    }

    @PostMapping("removeRecipeIngredients/{recipeId}")
    public String processRemoveRecipeIngredients(@PathVariable int recipeId, @RequestParam(required = false) List<Integer> recipeIngredientIds) {
        if(recipeIngredientIds != null && !recipeIngredientIds.isEmpty()) {
            recipeIngredientRepository.deleteAllById(recipeIngredientIds);
        }

        return "redirect:/recipes/RecipeIngredients/" + String.valueOf(recipeId);
    }

    @PostMapping("addRecipeStep/{recipeId}")
    public String processAddRecipeStep(@PathVariable int recipeId, @RequestParam String recipeStepText) {
        if(recipeStepText == "") return "redirect:/recipes/RecipeIngredients/" + String.valueOf(recipeId);

        recipeStepsRepository.save(new RecipeSteps(recipeId, recipeStepText));

        return "redirect:/recipes/RecipeIngredients/" + String.valueOf(recipeId);
    }

    @PostMapping("removeRecipeSteps/{recipeId}")
    public String processRemoveSteps(@PathVariable int recipeId, @RequestParam(required = false) List<Integer> recipeStepIds) {
        if(recipeStepIds != null && !recipeStepIds.isEmpty()) {
            recipeStepsRepository.deleteAllById(recipeStepIds);
        }

        return "redirect:/recipes/RecipeIngredients/" + String.valueOf(recipeId);
    }

    @GetMapping("editRecipes")
    public String editRecipesHome(Model model,HttpServletRequest request) {
        model.addAttribute("title", "El Editio de Recipio!");
        model.addAttribute("username", returnLoginName(request));
        model.addAttribute("login", returnLoginURL(request));
        model.addAttribute("recipes", recipeRepository.findAll());

        return "recipes/editRecipes";
    }

    @GetMapping("deleteRecipe/{recipeId}")
    public String processDeleteRecipe(@PathVariable int recipeId) {

        // CLEAR ANY RECIPE STEPS THAT REFERENCE THE RECIPE
        Iterable<RecipeSteps> recipeSteps = recipeStepsRepository.findByRecipeId(recipeId);
        List<Integer> recipeStepIds = new ArrayList<Integer>();
        for(RecipeSteps step : recipeSteps) {
            recipeStepIds.add(step.getId());
        }
        recipeStepsRepository.deleteAllById(recipeStepIds);

        // CLEAR ANY RECIPE INGREDIENTS THAT REFERENCE THE RECIPE
        Iterable<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findByRecipeId(recipeId);
        List<Integer> recipeIngredientIds = new ArrayList<Integer>();
        for(RecipeIngredient ingredient : recipeIngredients) {
            recipeIngredientIds.add(ingredient.getRecipeIngredientId());
        }
        recipeIngredientRepository.deleteAllById(recipeIngredientIds);

        // CLEAR THE RECIPE
        recipeRepository.deleteById(recipeId);

        return "redirect:/recipes/editRecipes";
    }
}
