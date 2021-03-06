package model;

import exceptions.IsEmptyException;

import java.util.*;

// Represents a collection of recipes
public class RecipeCollection {
    private LinkedList<Recipe> recipes;

    // EFFECTS: Initializes a RecipeCollection
    public RecipeCollection() {
        recipes = new LinkedList<>();
    }

    // EFFECTS: Takes a recipe and adds it into end of recipe collection returns true. I
    //          If recipe is already in the collection it will return false
    public boolean addRecipe(Recipe r) {
        if (recipes.contains(r)) {
            return false;
        }
        recipes.add(r);
        return true;
    }

    // EFFECTS: Takes a recipe and if found, returns true and removes the recipe from the collection.
    //          Returns false if given recipe is not found in the collection.
    public boolean removeRecipe(Recipe r) {
        if (recipes.contains(r)) {
            recipes.remove(r);
            return true;
        }
        return false;
    }

    // EFFECTS: Takes a recipe collection and returns a list of recipe titles in the collection
    public ArrayList<String> viewTitles() throws IsEmptyException {
        if (recipes.isEmpty()) {
            throw new IsEmptyException("Recipes cannot be empty");
        }
        ArrayList<String> titles = new ArrayList<String>();
        for (int i = 0; i <= (recipes.size() - 1); i++) {
            titles.add((recipes.get(i).getTitle()));
        }
        return titles;
    }

    // EFFECTS: takes a recipe title and returns the recipe with that title.
    //          If there is no recipe with that title, returns empty recipe
    public Recipe findRecipe(String title) {
        for (Recipe r : recipes) {
            if (r.getTitle().equals(title)) {
                return r;
            }
        }
        return null;
    }

    // EFFECTS: takes a recipe title and a rating, gives the rating value to the recipe returns true.
    //          If the recipe cannot be found, returns false
    public boolean rateRecipe(String title, int rating) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getTitle() == title) {
                recipes.get(i).giveRating(rating);
                return true;
            }
        }
        return false;
    }
}
