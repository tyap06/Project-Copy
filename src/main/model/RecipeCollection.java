package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

// Represents a collection of recipes
public class RecipeCollection {
    private LinkedList<Recipe> recipes;

    // EFFECTS: Initializes a RecipeCollection
    public RecipeCollection() {
        recipes = new LinkedList<>();
    }

    // EFFECTS: Takes a recipe and adds it into end of recipe collection
    public void addRecipe(Recipe r) {
        recipes.addLast(r);
    }

    // EFFECTS: Takes a recipe and if found, returns true and removes the recipe from the collection.
    //          Returns false if given recipe is not found in the collection.
    public boolean removeRecipe(Recipe r) {
        return true; //stub
    }

    // EFFECTS: Takes a recipe collection and returns a list of recipe titles in the collection
    public ArrayList<String> viewTitles(RecipeCollection rc) {
        ArrayList<String> titles = new ArrayList<String>();
        return titles; //stub
    }

    // EFFECTS: takes a recipe title and returns the recipe with that title.
    //          If there is no recipe with that title, returns empty recipe
    public Optional<Recipe> findRecipe(String title) {
        return Optional.empty(); //stub
    }

    // EFFECTS: takes a recipe title and a rating, gives the rating value to the recipe returns true.
    //          If the recipe cannot be found, returns false
    public boolean rateRecipe(String title, int rating) {
        return true; //stub
    }
}
