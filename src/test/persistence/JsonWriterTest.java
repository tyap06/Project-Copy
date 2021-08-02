package persistence;

import model.Recipe;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// JsonSerializationDemo code used in parts of my code
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WorkRoom wr = new WorkRoom("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numRecipes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        ArrayList<String> i0 = new ArrayList<String>(Arrays.asList("2 large eggs", "Salt", "Pepper"));
        ArrayList<String> i1 = new ArrayList<String>(Arrays.asList("2 slices of bread", "Cheese", "Butter"));
        ArrayList<String> d0 = new ArrayList<String>(Arrays.asList("Crack eggs into bowl", "Season and whisk eggs",
                "Cook eggs in a pan", "Serve and enjoy"));
        ArrayList<String> d1 = new ArrayList<String>(Arrays.asList("Spread butter one side of each bread slice",
                "In a pan, add the slices of bread", "Add cheese on both slices of bread",
                "Flip one bread slice on top of the other slice", "Cook until cheese is melted", "Serve and enjoy"));
        Recipe r1 = new Recipe("Omelet", 1, i0, 5, 10, d0, 4);
        Recipe r2 = new Recipe("Grilled Cheese", 1, i1, 5, 5, d1, 3);
        try {
            WorkRoom wr = new WorkRoom("My work room");
            wr.addRecipe(r1);
            wr.addRecipe(r2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Recipe> recipes = wr.getRecipes();
            assertEquals(2, recipes.size());
            checkRecipe("Omelet", 1, i0, 5, 10, d0, 4, recipes.get(0));
            checkRecipe("Grilled Cheese", 1, i1, 5, 5, d1, 3, recipes.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
