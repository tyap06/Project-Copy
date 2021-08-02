package persistence;

import model.Recipe;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe(String title, int servingSize, ArrayList<String> ingredients,
                               int prepTime, int cookTime, ArrayList<String> directions, int rating, Recipe recipe){
        assertEquals(title, recipe.getTitle());
        assertEquals(servingSize, recipe.getServingSize());
        assertEquals(ingredients, recipe.getIngredients());
        assertEquals(prepTime, recipe.getPrepTime());
        assertEquals(cookTime, recipe.getCookTime());
        assertEquals(directions, recipe.getDirections());
        assertEquals(rating, recipe.getRating());
    }
}
