package persistence;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import model.Recipe;
import model.WorkRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile(){
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try{
            WorkRoom wr = reader.read();
            fail("IOException expected");
        } catch (IOException e){
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom(){
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try{
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            assertEquals(0, wr.numRecipes());
        } catch (IOException e){
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom(){
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try{
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Recipe> recipes = wr.getRecipes();
            assertEquals(2, recipes.size());
            ArrayList<String> i0 = new ArrayList<String>(Arrays.asList("2 large eggs", "Salt", "Pepper"));
            ArrayList<String> i1 = new ArrayList<String>(Arrays.asList("2 slices of bread", "Cheese", "Butter"));
            ArrayList<String> d0 = new ArrayList<String>(Arrays.asList("Crack eggs into bowl", "Season and whisk eggs",
                    "Cook eggs in a pan", "Serve and enjoy"));
            ArrayList<String> d1 = new ArrayList<String>(Arrays.asList("Spread butter one side of each bread slice",
                    "In a pan, add the slices of bread", "Add cheese on both slices of bread",
                    "Flip one bread slice on top of the other slice", "Cook until cheese is melted", "Serve and enjoy"));
            checkRecipe("Omelet", 1, i0, 5, 10, d0, 4, recipes.get(0) );
            checkRecipe("Grilled Cheese", 1, i1, 5, 5, d1, 3, recipes.get(1));
        }catch (IOException e){
            fail("Couldn't read from file");
        }
    }
}
