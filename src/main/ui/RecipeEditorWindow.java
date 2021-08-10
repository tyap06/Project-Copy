package ui;

import model.Recipe;
import model.RecipeCollection;
import model.WorkRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Recipe display and editor window
public class RecipeEditorWindow extends JFrame {
    private Recipe recipe;
    private String fileName;
    private RecipeCollection recipes;
    private WorkRoom workRoom;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private JPanel recipeDisplayPanel;

    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JPanel buttonPanel;

    // MODIFIES: this
    // EFFECTS: initializes recipe display panel
    public RecipeEditorWindow(Recipe recipe, String fileName) {
        super("Recipe Manager App");

        this.recipe = recipe;
        this.fileName = fileName;

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
        doLoadWorkRoom();
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

        addSaveButton();
        addCloseButton();

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes save button and adds it to the button panel
    private void addSaveButton() {
        JButton saveButton = new JButton("Save Recipe");
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
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved recipe to " +  workRoom.getName());
            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
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
            super("Save recipe");
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

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void doLoadWorkRoom() {
        try {
            JsonReader reader = new JsonReader(JSON_STORE);
            workRoom = reader.read();
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
