package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a recipe having a title, serving size, ingredients, prep time, cook time, directions, and rating
public class Recipe implements Writable {
    private String title;
    private int servingSize;
    private ArrayList<String> ingredients;
    private int prepTime;                  // time in minutes
    private int cookTime;                  // time in minutes
    private ArrayList<String> directions;
    private int rating;

    // EFFECTS: recipe has a title, serving size, ingredients, prep time, cook time,
    // directions, and a default rating of 0
    public Recipe(String title, int servingSize, ArrayList<String> ingredients,
                  int prepTime, int cookTime, ArrayList<String> directions, int rating) {
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

    // EFFECTS: returns ingredient list
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    // EFFECTS: returns prep time
    public int getPrepTime() {
        return prepTime;
    }

    // EFFECTS: returns cook time
    public int getCookTime() {
        return cookTime;
    }

    // EFFECTS: returns directions
    public ArrayList<String> getDirections() {
        return directions;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("serving size", servingSize);
        json.put("ingredients", ingredients);
        json.put("prep time", prepTime);
        json.put("cook time", cookTime);
        json.put("directions", directions);
        json.put("rating", rating);
        return json;
    }

}
