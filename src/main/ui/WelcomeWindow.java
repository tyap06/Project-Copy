package ui;

import model.Recipe;
import model.RecipeCollection;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class WelcomeWindow extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;

    private Recipe recipe;
    private RecipeCollection recipes = new RecipeCollection();
    private WorkRoom workRoom = new WorkRoom("Timothy's workroom");
    private String fileName;

    private static final String JSON_STORE = "./data/workroom.json";
    private static final String NEW_JSON_STORE = "./data/";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    // MODIFIES: this
    // EFFECTS: initializes fields and graphics of frame
    public WelcomeWindow() {
        super("Recipe Manager App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initializes frame graphics
    private void initializeGraphics() {
        setLayout(new GridLayout(0, 1));
        setSize(new Dimension(WIDTH, HEIGHT));
        addComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons and labels to the panel
    private void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setSize(new Dimension(0, 0));
        add(panel);
        JLabel label = new JLabel("Recipe Manager App");
        label.setFont(new Font("Roboto", Font.PLAIN, 26));
        label.setHorizontalAlignment(0);
        panel.add(label);
        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        JButton makeRecipeButton = new JButton(new MakeRecipeAction());
        makeRecipeButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        JButton loadRecipeButton = new JButton(new LoadRecipesAction());
        loadRecipeButton.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        buttonPanel.add(makeRecipeButton);
        buttonPanel.add(loadRecipeButton);
        add(buttonPanel);
    }

    // REQUIRES: user is prompted to enter a new recipe
    // MODIFIES: this
    // EFFECTS: initializes a new Recipe and displays it
    public void makeNewRecipe(String title, int servingSize, ArrayList<String> ingredients,
                              int prepTime, int cookTime, ArrayList<String> directions, int rating) {
        recipe = new Recipe(title, servingSize, ingredients, prepTime, cookTime, directions, rating);
        launchRecipeDisplayWindow();
    }

    // MODIFIES: this
    // EFFECTS: loads recipes from file and displays it
    //        if recipe not found, restart
    public void loadRecipes(String name) {
        try {
            JsonReader reader = new JsonReader(NEW_JSON_STORE + name + ".json");
            workRoom = reader.read();
            launchRecipeDisplayWindow();
        } catch (IOException e) {
            new WelcomeWindow();
        }
    }

    // MODIFIES: this
    // EFFECTS: closes current window and launches recipes to display
    public void launchRecipeDisplayWindow() {
        RecipeEditorWindow recipeEditor = new RecipeEditorWindow(recipe, workRoom);
    }

    // MODIFIES: this
    // EFFECTS: closes window
    public void closeWindow() {
        setVisible(false);
    }

    // this class allows user to input a recipe in a pop up window
    private class MakeRecipeAction extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: Constructs class
        MakeRecipeAction() {
            super("Make Recipe");
        }

        // REQUIRES: users follow the input format
        // MODIFIES: this
        // EFFECTS: gets user input and makes a new recipe
        public void actionPerformed(ActionEvent e) {
            closeWindow();
            String title = JOptionPane.showInputDialog(null, "Enter new recipe title",
                    "Recipe title", JOptionPane.QUESTION_MESSAGE);
            String servingSize = JOptionPane.showInputDialog(null, "Enter serving size",
                    "Serving size", JOptionPane.QUESTION_MESSAGE);
            ArrayList<String> ingredients = getIngredients();
            String prepTime = JOptionPane.showInputDialog(null, "Enter prep time in minutes",
                    "Prep time", JOptionPane.QUESTION_MESSAGE);
            String cookTime = JOptionPane.showInputDialog(null, "Enter cook time in minutes",
                    "Cook time", JOptionPane.QUESTION_MESSAGE);
            ArrayList<String> directions = getDirections();
            String rating = JOptionPane.showInputDialog(null, "Enter rating",
                    "Rating", JOptionPane.QUESTION_MESSAGE);
            Recipe newRecipe = new Recipe(title, Integer.parseInt(servingSize), ingredients,
                    Integer.parseInt(prepTime), Integer.parseInt(cookTime), directions, Integer.parseInt(rating));
            workRoom.addRecipe(newRecipe);
            makeNewRecipe(title, Integer.parseInt(servingSize), ingredients, Integer.parseInt(prepTime),
                    Integer.parseInt(cookTime), directions, Integer.parseInt(rating));
        }
    }

    // EFFECTS: prompts user to create an ingredient list, returns a string array list
    private ArrayList<String> getIngredients() {
        int addIngredient;
        ArrayList<String> ingredients = new ArrayList<String>();
        String ingredient;
        do {
            ingredient = JOptionPane.showInputDialog(null,
                    "Enter ingredients",
                    "Ingredients",
                    JOptionPane.QUESTION_MESSAGE);
            ingredients.add(ingredient);

            // prompt user to add another ingredient
            addIngredient = JOptionPane.showConfirmDialog(null, "Do you want to add another ingredient?",
                    "Add more ingredients?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        } while (addIngredient == JOptionPane.YES_OPTION);
        return ingredients;
    }

    // EFFECTS: prompts user to add directions, returns string array list
    private ArrayList<String> getDirections() {
        int addDirection;
        ArrayList<String> directions = new ArrayList<String>();
        String direction;
        do {
            direction = JOptionPane.showInputDialog(null,
                    "Enter directions",
                    "Directions",
                    JOptionPane.QUESTION_MESSAGE);
            directions.add(direction);

            // prompt user to add another direction
            addDirection = JOptionPane.showConfirmDialog(null, "Do you want to add another direction?",
                    "Add more directions?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        } while (addDirection == JOptionPane.YES_OPTION);
        return directions;
    }

    // loads recipes
    private class LoadRecipesAction extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: initializes class name
        LoadRecipesAction() {
            super("Load recipes");
        }

        // MODIFIES: this
        public void actionPerformed(ActionEvent e) {
            closeWindow();
            String name = JOptionPane.showInputDialog(null,
                    "Enter Recipe collection name:",
                    "Load Recipes",
                    JOptionPane.QUESTION_MESSAGE);
            loadRecipes(name);
        }
    }
}
