package model;

import java.util.ArrayList;

// Represents a recipe having a title, serving size, ingredients, prep time, cook time, directions, and rating
public class Recipe {
    private String title;
    private int servingSize;
    private ArrayList<Ingredient> ingredients;
    private int prepTime;
    private int cookTime;
    private ArrayList<Direction> directions;
    private int rating;

    // EFFECTS: recipe has a title, serving size, ingredients, prep time, cook time,
    // directions, and a default rating of 0
    public Recipe(String title, int servingSize, ArrayList<Ingredient> ingredients,
                  int prepTime, int cookTime, ArrayList<Direction> directions, int rating) {
        this.title = title;
        this.servingSize = servingSize;
        this.ingredients = ingredients;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.directions = directions;
        this.rating = rating;
    }

    // EFFECTS: returns title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns serving size
    public int getServingSize() {
        return servingSize;
    }

    // EFFECTS: returns prep time
    public int getPrepTime() {
        return prepTime;
    }

    // EFFECTS: returns cook time
    public int getCookTime() {
        return cookTime;
    }

    // EFFECTS: returns rating
    public int getRating() {
        return rating;
    }
}
