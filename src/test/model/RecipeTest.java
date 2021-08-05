package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class RecipeTest {
    Recipe r1;

    @BeforeEach
    void runBefore() {
        ArrayList<String> ri = new ArrayList<String>(Arrays.asList("2 large eggs", "Kosher salt",
                "Freshly ground black pepper", "Pinch red pepper flakes", "2 tbsp. butter", "1/4 cup shredded cheddar",
                "2 tbsp. freshly chopped chives"));
        ArrayList<String> rd = new ArrayList<String>(Arrays.asList("In a medium bowl, beat eggs until no whites remain, then season with salt, pepper, and a pinch red pepper flakes",
                "In a medium non-stick skillet over medium heat, melt butter. Pour in eggs and tilt pan so eggs fully cover the entire pan." +
                        "As eggs start to set, use a rubber spatula to drag cooked edges into center of pan. " +
                        "Tilt pan to let uncooked egg fall to the edge of the pan. ", "Once the bottom is set, but top is still a little wet, " +
                        "sprinkle cheese and chives on one half of omelet. Fold other side over cheese and slide omelet onto a plate. "));
        r1 = new Recipe( "Perfect Omelet", 1, ri, 5, 10, rd, 0);
    }

    @Test
    void testGiveRating(){
        r1.giveRating(4);
        assertEquals(4, r1.getRating());
    }

    @Test
    void testGetServingSize(){
        assertEquals(1, r1.getServingSize());
    }

    @Test
    void testGetIngredients(){
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("2 large eggs", "Kosher salt",
                "Freshly ground black pepper", "Pinch red pepper flakes", "2 tbsp. butter", "1/4 cup shredded cheddar",
                "2 tbsp. freshly chopped chives"));
        assertEquals(expected, r1.getIngredients());
    }

    @Test
    void testGetPrepTime(){
        assertEquals(5, r1.getPrepTime());
    }

    @Test
    void testGetCookTime(){
        assertEquals(10, r1.getCookTime());
    }

    @Test
    void testGetDirections() {
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("In a medium bowl, beat eggs " +
                        "until no whites remain, then season with salt, pepper, and a pinch red pepper flakes",
                "In a medium non-stick skillet over medium heat, melt butter. Pour in eggs and tilt pan so eggs fully cover the entire pan." +
                        "As eggs start to set, use a rubber spatula to drag cooked edges into center of pan. " +
                        "Tilt pan to let uncooked egg fall to the edge of the pan. ", "Once the bottom is set, but top is still a little wet, " +
                        "sprinkle cheese and chives on one half of omelet. Fold other side over cheese and slide omelet onto a plate. "));
        assertEquals(expected, r1.getDirections());
    }

    @Test
    void testToJson() {
        JSONObject jsonObject = r1.toJson();
        assertEquals(r1.getTitle(), jsonObject.get("title"));
        assertEquals(r1.getServingSize(), jsonObject.get("serving size"));
        JSONArray jsonArrayIngredients = jsonObject.getJSONArray("ingredients");
        assertEquals(r1.getIngredients(), r1.getArrayListString(jsonArrayIngredients));
        assertEquals(r1.getPrepTime(), jsonObject.get("prep time"));
        assertEquals(r1.getCookTime(), jsonObject.get("cook time"));
        JSONArray jsonArrayDirections = jsonObject.getJSONArray("directions");
        assertEquals(r1.getDirections(), r1.getArrayListString(jsonArrayDirections));
        assertEquals(r1.getRating(), jsonObject.get("rating"));
    }
}