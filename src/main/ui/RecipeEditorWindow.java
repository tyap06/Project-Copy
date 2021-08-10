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

// Recipe display and editor window
public class RecipeEditorWindow extends JFrame {
    private Recipe recipe;
    private RecipeCollection recipes;
    private WorkRoom workRoom;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private JPanel recipeDisplayPanel;

    private static final String JSON_STORE = "./data/workroom.json";
    private static final String NEW_JSON_STORE = "./data/";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JPanel buttonPanel;

    // MODIFIES: this
    // EFFECTS: initializes recipe display panel
    public RecipeEditorWindow(Recipe recipe, WorkRoom workRoom) {
        super("Recipe Manager App");

        this.recipe = recipe;
        this.workRoom = workRoom;

        initializeRecipeGraphics(false);

        addButtonPane();
        addRecipeDisplayPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    // EFFECTS: initializes recipe graphics
    //          if update is true, removes panel to start over
    private void initializeRecipeGraphics(Boolean update) {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        if (update) {
            remove(recipeDisplayPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds panel to this window and displays recipe information
    private void addRecipeDisplayPanel() {
        recipeDisplayPanel = new JPanel(new GridLayout(0, 1));
        for (Recipe recipe : workRoom.getRecipes()) {
            JLabel title = new JLabel(recipe.getTitle());
            JLabel servingSize = new JLabel("Serving size: " + Integer.toString(recipe.getServingSize()));
            JLabel ingredients = new JLabel("Ingredients: " + recipe.getIngredients());
            JLabel prepTime = new JLabel("Prep time: " + Integer.toString(recipe.getPrepTime()));
            JLabel cookTime = new JLabel("Cook time: " + Integer.toString(recipe.getCookTime()));
            JLabel directions = new JLabel("Directions: " + recipe.getDirections());
            JLabel rating = new JLabel("Rating: " + Integer.toString(recipe.getRating()));
            title.setFont(new Font(Font.DIALOG,Font.BOLD, 20));
            recipeDisplayPanel.add(title);
            recipeDisplayPanel.add(servingSize);
            recipeDisplayPanel.add(ingredients);
            recipeDisplayPanel.add(prepTime);
            recipeDisplayPanel.add(cookTime);
            recipeDisplayPanel.add(directions);
            recipeDisplayPanel.add(rating);
        }
        add(recipeDisplayPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes button panel
    private void addButtonPane() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));

        addRecipeButton();
        addSaveButton();
        addCloseButton();

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes Add Recipe button and adds it to the button panel
    private void addRecipeButton() {
        JButton addRecipe = new JButton("Add Recipe");
        addRecipe.setBorderPainted(true);
        addRecipe.setFocusPainted(true);
        addRecipe.setContentAreaFilled(true);
        addRecipe.addActionListener(new AddRecipeAction());
        buttonPanel.add(addRecipe);
    }

    // MODIFIES: this
    // EFFECTS: initializes save button and adds it to the button panel
    private void addSaveButton() {
        JButton saveButton = new JButton("Save Recipe Collection");
        saveButton.setBorderPainted(true);
        saveButton.setFocusPainted(true);
        saveButton.setContentAreaFilled(true);
        saveButton.addActionListener(new SaveRecipeAction());
        buttonPanel.add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes close button and adds it to the button panel, allows user to go back to the welcome window
    private void addCloseButton() {
        JButton closeButton = new JButton("Close");
        closeButton.setBorderPainted(true);
        closeButton.setFocusPainted(true);
        closeButton.setContentAreaFilled(true);
        closeButton.addActionListener(new CloseAction());
        buttonPanel.add(closeButton);
    }


    // EFFECTS: saves the recipe workroom to file
    public void doSaveWorkRoom() {
        try {
            String name = JOptionPane.showInputDialog(null, "Enter a name to save recipe collection: ",
                    "Recipe Collection Name", JOptionPane.QUESTION_MESSAGE);
            jsonWriter = new JsonWriter(NEW_JSON_STORE + name + ".json");
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,
                    "Saved recipe to " + NEW_JSON_STORE + name + ".json");
            System.out.println("Saved recipe to " + NEW_JSON_STORE + name + ".json");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write file: " + JSON_STORE);
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private class SaveRecipeAction extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: constructs save recipe action
        SaveRecipeAction() {
            super("Save recipe collection");
        }


        // MODIFIES: this
        // EFFECTS: gets user input for recipe and saves recipe
        public void actionPerformed(ActionEvent e) {
            doSaveWorkRoom();
        }

    }

    private class CloseAction extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: constructs save recipe action
        CloseAction() {
            super("Close");
        }


        // MODIFIES: this
        // EFFECTS: gets user input for recipe and saves recipe
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            WelcomeWindow launch = new WelcomeWindow();
        }

    }

    // this class allows user to input a recipe in a pop up window
    private class AddRecipeAction extends AbstractAction {

        // MODIFIES: this
        // EFFECTS: Constructs class
        AddRecipeAction() {
            super("Add Recipe");
        }

        // REQUIRES: users follow the input format
        // MODIFIES: this
        // EFFECTS: gets user input and makes a new recipe
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
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
            RecipeEditorWindow launch = new RecipeEditorWindow(newRecipe, workRoom);

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

}
