package persistence;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import jdk.nashorn.internal.parser.JSONParser;
import model.Recipe;
import model.WorkRoom;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom form JSON data stored in file
// Code is based on JsonSerializationDemo provided in phase 2 instructions
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Recipe from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject((jsonData));
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private WorkRoom parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkRoom wr = new WorkRoom(name);
        addRecipes(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses recipes from JSON object and adds them to the workroom
    private void addRecipes(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipes");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(wr, nextRecipe);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses recipe from JSON object and adds it to workroom
    private void addRecipe(WorkRoom wr, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int servingSize = jsonObject.getInt("serving size");
        JSONArray ingredientArray = jsonObject.getJSONArray("ingredients");
        ArrayList<String> ingredients = new ArrayList<String>();
        for (int i = 0; i < ingredientArray.length(); i++) {
            ingredients.add(ingredientArray.getString(i));
        }
        int prepTime = jsonObject.getInt("prep time");
        int cookTime = jsonObject.getInt("cook time");
        JSONArray directionArray = jsonObject.getJSONArray("directions");
        ArrayList<String> directions = new ArrayList<String>();
        for (int i = 0; i < directionArray.length(); i++) {
            directions.add(directionArray.getString(i));
        }
        int rating = jsonObject.getInt("rating");
        Recipe recipe = new Recipe(title, servingSize, ingredients, prepTime, cookTime, directions, rating);
        wr.addRecipe(recipe);
    }
}
