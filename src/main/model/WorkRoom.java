package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of recipes
public class WorkRoom implements Writable {
    private String name;
    private List<Recipe> recipes;

    // EFFECTS: constructs workroom with a name and empty list of recipes
    public WorkRoom(String name) {
        this.name = name;
        recipes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds recipe to this workroom
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    // MODIFIES: this
    // EFFECTS: returns an unmodifiable list of recipes in this workroom
    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }

    // EFFECTS: returns a list of all recipe titles in this workroom
    public ArrayList<String> viewTitles(List<Recipe> recipes) {
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


    // EFFECTS: returns a number of recipes in this workroom
    public int numRecipes() {
        return recipes.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: returns recipes in this workroom as a JSON array
    public JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipes) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }
}
