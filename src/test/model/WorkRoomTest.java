package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkRoomTest {
    private WorkRoom w1;
    private Recipe r1;
    private Recipe r2;

    @BeforeEach
    void runBefore() {
        w1 = new WorkRoom("Timothy's Workroom");
        ArrayList<String> ri = new ArrayList<String>(Arrays.asList("2 large eggs", "Kosher salt",
                "Freshly ground black pepper", "Pinch red pepper flakes", "2 tbsp. butter", "1/4 cup shredded cheddar",
                "2 tbsp. freshly chopped chives"));
        ArrayList<String> rd = new ArrayList<String>(Arrays.asList("In a medium bowl, beat eggs until no whites remain, then season with salt, pepper, and a pinch red pepper flakes",
                "In a medium non-stick skillet over medium heat, melt butter. Pour in eggs and tilt pan so eggs fully cover the entire pan." +
                        "As eggs start to set, use a rubber spatula to drag cooked edges into center of pan. " +
                        "Tilt pan to let uncooked egg fall to the edge of the pan. ", "Once the bottom is set, but top is still a little wet, " +
                        "sprinkle cheese and chives on one half of omelet. Fold other side over cheese and slide omelet onto a plate. "));
        r1 = new Recipe("Perfect Omelet", 1, ri, 5, 10, rd, 0);
        ArrayList<String> ri2 = new ArrayList<String>(Arrays.asList("1 pound of ground beef", "1 onion chopped", "4 cloves garlic, minced",
                "1 small green bell pepper, diced", "1 (28 ounce) can diced tomatoes", "1 (16 ounce) can tomato paste", "2 teaspoons dried oregano",
                "2 teaspoons dried basil", "1 teaspoon salt"));
        ArrayList<String> rd2 = new ArrayList<String>(Arrays.asList("Combine ground beef, onion, garlic, and green pepper " +
                "in a large saucepan. Cook and stir until meat is brown and vegetables are tender. Drain grease.", "Stir diced tomatoes, tomato sauce, " +
                "and tomato paste into the pan. Season with oregano, basil, salt, and pepper. Simmer spaghetti sauce for 1 hour, stirring occasionally."));
        r2 = new Recipe("Spaghetti Sauce with Ground Beef", 8, ri2, 15, 70, rd2, 0);
    }

    @Test
    void testGetName() {
        assertEquals("Timothy's Workroom", w1.getName());
    }

    @Test
    void testAddRecipe() {
        w1.addRecipe(r1);
        assertEquals(w1.getRecipes().get(0), r1);
    }

    @Test
    void testViewTitles() {
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("Perfect Omelet", "Spaghetti Sauce with Ground Beef"));
        w1.addRecipe(r1);
        w1.addRecipe(r2);
        List<Recipe> recipes = w1.getRecipes();
        assertEquals(expected, w1.viewTitles(recipes));
    }

    @Test
    void testFindRecipe() {
        w1.addRecipe(r1);
        w1.addRecipe(r2);
        assertEquals(r1, w1.findRecipe("Perfect Omelet"));
        assertNull(w1.findRecipe("Grilled Cheese"));
    }

    @Test
    void testNumRecipes() {
        w1.addRecipe(r1);
        w1.addRecipe(r2);
        assertEquals(2, w1.numRecipes());
    }

    @Test
    void testToJson() {
        w1.addRecipe(r1);
        w1.addRecipe(r2);
        w1.toJson();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(r1);
        recipes.add(r2);
        JSONObject expected = new JSONObject();
        expected.put("name", "Timothy's Workroom");
        expected.put("recipes", w1.recipesToJson());
    }
}
