package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RecipeCollectionTest {
    Recipe r1;
    Recipe r2;
    Recipe r3;
    RecipeCollection rc1;
    RecipeCollection rc2;

    @BeforeEach
    void runBefore() {
        ArrayList<String> ri1 = new ArrayList<String>(Arrays.asList("2 large eggs", "Kosher salt",
                "Freshly ground black pepper", "Pinch red pepper flakes", "2 tbsp. butter", "1/4 cup shredded cheddar",
                "2 tbsp. freshly chopped chives"));
        ArrayList<String> rd1 = new ArrayList<String>(Arrays.asList("In a medium bowl, beat eggs until no whites remain, then season with salt, pepper, and a pinch red pepper flakes",
                "In a medium non-stick skillet over medium heat, melt butter. Pour in eggs and tilt pan so eggs fully cover the entire pan." +
                        "As eggs start to set, use a rubber spatula to drag cooked edges into center of pan. " +
                        "Tilt pan to let uncooked egg fall to the edge of the pan. ", "Once the bottom is set, but top is still a little wet, " +
                        "sprinkle cheese and chives on one half of omelet. Fold other side over cheese and slide omelet onto a plate."));
        ArrayList<String> ri2 = new ArrayList<String>(Arrays.asList("1 pound of ground beef", "1 onion chopped", "4 cloves garlic, minced",
                "1 small green bell pepper, diced", "1 (28 ounce) can diced tomatoes", "1 (16 ounce) can tomato paste", "2 teaspoons dried oregano",
                "2 teaspoons dried basil", "1 teaspoon salt"));
        ArrayList<String> rd2 = new ArrayList<String>(Arrays.asList("Combine ground beef, onion, garlic, and green pepper " +
                "in a large saucepan. Cook and stir until meat is brown and vegetables are tender. Drain grease.", "Stir diced tomatoes, tomato sauce, " +
                "and tomato paste into the pan. Season with oregano, basil, salt, and pepper. Simmer spaghetti sauce for 1 hour, stirring occasionally."));
        ArrayList<String> ri3 = new ArrayList<String>(Arrays.asList("2 tablespoons coconut oil", "1 (16 ounce) package skinless, " +
                        "boneless chicken breast halves, cut into small cubes", "1 (14 ounce) can cream of coconut", "1 (11 ounce) bottle red Thai curry sauce",
                "½ (16 ounce) package dried rice stick vermicelli noodles"));
        ArrayList<String> rd3 = new ArrayList<String>(Arrays.asList("Heat oil in a large skillet on high heat. " +
                "Add chicken cubes; cook until browned, about 2 minutes per side. Reduce heat to medium-high and add coconut cream and curry sauce. Cook until chicken " +
                "is no longer pink in the center and the juices run clear, about 5 minutes. An instant-read thermometer inserted into the center should read at " +
                "least 165 degrees F (74 degrees C).", "Fill a large pot with lightly salted water and bring to a rolling boil; stir in vermicelli pasta and return to a boil. " +
                "Cook pasta uncovered, stirring occasionally, until the pasta is tender yet firm to the bite, 4 to 5 minutes. Drain.", "Reduce skillet heat to simmer. Add the noodles " +
                "and let simmer until flavors are absorbed, about 5 minutes. Divide chicken and noodles among individual serving bowls."));
        r1 = new Recipe("Perfect Omelet", 1, ri1, 5, 10, rd1, 0);
        r2 = new Recipe("Spaghetti Sauce with Ground Beef", 8,ri2, 15, 70, rd2, 0);
        r3 = new Recipe("Five-Ingredient Red Curry Chicken", 6,ri3, 10, 20, rd3, 0);
        rc1 = new RecipeCollection();
        rc2 = new RecipeCollection();
    }

    @Test
    void testAddRecipe() {
        assertTrue(rc1.addRecipe(r1));
    }

    @Test
    void testAddRecipeDuplicate() {
        rc1.addRecipe(r2);
        assertFalse(rc1.addRecipe(r2));
    }

    @Test
    void testRemoveRecipeInCollection(){
        rc1.addRecipe(r1);
        rc1.addRecipe(r2);
        rc1.addRecipe(r3);
        assertTrue(rc1.removeRecipe(r1));
    }

    @Test
    void testRemoveRecipeNotFoundInCollection(){
        rc1.addRecipe(r1);
        rc1.addRecipe(r3);
        assertFalse(rc1.removeRecipe(r2));
    }

    @Test
    void testViewTitles() {
        List<String> expected = Arrays.asList("Spaghetti Sauce with Ground Beef",
                "Five-Ingredient Red Curry Chicken");
        rc2.addRecipe(r2);
        rc2.addRecipe(r3);
        assertEquals(expected, rc2.viewTitles());
    }

    @Test
    void testFindRecipe(){
        rc1.addRecipe(r1);
        rc1.addRecipe(r3);
        assertEquals(r1, rc1.findRecipe(r1.getTitle()));
        assertEquals(null, rc1.findRecipe(r2.getTitle()));
    }

    @Test
    void testRateRecipe(){
        rc1.addRecipe(r1);
        rc1.addRecipe(r2);
        assertTrue(rc1.rateRecipe("Perfect Omelet", 4));
        assertEquals(4, r1.getRating());
        assertFalse(rc1.rateRecipe("Five-Ingredient Red Curry Chicken", 5));
        assertEquals(0, r3.getRating());
    }
}


