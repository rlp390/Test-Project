package org.launchcode.TestProject.models.Recipes.Enums;

public enum RecipeUOM {
    TSP("tsp"),
    TBSP("Tbsp"),
    CUP("Cup"),
    FLOZ("Fl Oz"),
    OUNCE("Oz"),
    PINT("Pt"),
    QUART("Qt");

    private final String displayName;

    RecipeUOM(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
