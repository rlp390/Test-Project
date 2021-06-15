package org.launchcode.TestProject.models.Recipes.Enums;

public enum IngredientType {
    SPICE("Spice"),
    HERB("Herb"),
    PRODUCE("Produce"),
    MEAT("Meat"),
    DAIRY("Dairy"),
    OTHER("Other");

    private final String displayName;

    IngredientType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }



}
