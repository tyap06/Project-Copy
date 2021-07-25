package model;

import java.util.ArrayList;
import java.util.List;

// Represents a recipe having a title, serving size, ingredients, prep time, cook time, directions, and rating
public class Recipe {
    private String title;
    private int servingSize;
    private List<String> ingredients;
    private int prepTime;                  // time in minutes
    private int cookTime;                  // time in minutes
    private List<String> directions;
    private int rating;

    // EFFECTS: recipe has a title, serving size, ingredients, prep time, cook time,
    // directions, and a default rating of 0
    public Recipe(String title, int servingSize, List<String> ingredients,
                  int prepTime, int cookTime, List<String> directions, int rating) {
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

    // REQUIRES: rating must be <= 5
    // EFFECTS: returns rating
    public int getRating() {
        return rating;
    }

    // REQUIRES: rating must be <= 5
    // EFFECTS: give a rating to a recipe
    public void giveRating(int r) {
        rating = r;
    }

}
