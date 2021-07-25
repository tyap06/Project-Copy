package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class RecipeTest {
    Recipe r1;

    @BeforeEach
    void runBefore() {
        r1 = new Recipe( "Perfect Omelet", 1, Arrays.asList("2 large eggs", "Kosher salt",
                "Freshly ground black pepper", "Pinch red pepper flakes", "2 tbsp. butter", "1/4 cup shredded cheddar",
                "2 tbsp. freshly chopped chives"), 5, 10,
                Arrays.asList("In a medium bowl, beat eggs until no whites remain, then season with salt, pepper, and a pinch red pepper flakes",
                        "In a medium non-stick skillet over medium heat, melt butter. Pour in eggs and tilt pan so eggs fully cover the entire pan." +
                                "As eggs start to set, use a rubber spatula to drag cooked edges into center of pan. " +
                                "Tilt pan to let uncooked egg fall to the edge of the pan. ", "Once the bottom is set, but top is still a little wet, " +
                                "sprinkle cheese and chives on one half of omelet. Fold other side over cheese and slide omelet onto a plate. "), 0);
    }

    @Test
    void testGiveRating(){
        r1.giveRating(4);
        assertEquals(4, r1.getRating());
    }

}