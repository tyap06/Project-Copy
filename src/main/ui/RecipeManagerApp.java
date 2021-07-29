package ui;

import model.Recipe;
import model.RecipeCollection;

import java.util.*;

// Recipe manager application - code based on ui in Teller app
public class RecipeManagerApp {
    private RecipeCollection recipes;
    private Recipe recipe;
    private Scanner input;

    // EFFECTS: runs recipe manager application
    public RecipeManagerApp() {
        runRecipeManager();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRecipeManager() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            doViewRecipes();
        } else if (command.equals("s")) {
            doSelectRecipe();
        } else if (command.equals("a")) {
            doAddRecipe();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes recipe collection and recipe
    private void init() {
        ArrayList<String> ri = new ArrayList<String>(Arrays.asList("1 pound of ground beef",
                "1 onion chopped", "4 cloves garlic, minced", "1 small green bell pepper, diced",
                "1 (28 ounce) can diced tomatoes", "1 (16 ounce) can tomato paste", "2 teaspoons dried oregano",
                "2 teaspoons dried basil", "1 teaspoon salt"));
        ArrayList<String> rd = new ArrayList<String>(Arrays.asList("Combine ground beef, onion, garlic, and "
                        + "green pepper in a large saucepan. Cook and stir until "
                        + "meat is brown and vegetables are tender. Drain grease.",
                "Stir diced tomatoes, tomato sauce,and tomato paste into the pan.",
                " Season with oregano, basil, salt, and pepper. Simmer spaghetti sauce for 1 hour, "
                        + "stirring occasionally."));
        recipes = new RecipeCollection();
        recipe = new Recipe("Spaghetti Sauce with Ground Beef", 8, ri, 15, 70, rd, 0);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View recipes");
        System.out.println("\ts -> Select recipe");
        System.out.println("\ta -> Add recipe");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Shows a list of recipe titles stored in recipe
    private void doViewRecipes() {
        System.out.println("Recipes:" + recipes.viewTitles());
    }

    // MODIFIES: this
    // EFFECTS: allows user to select a recipe from the recipe collection
    private void doSelectRecipe() {
        System.out.println("Recipes:" + recipes.viewTitles());
        System.out.println("\nEnter the title of the recipe in the recipe collection above:");
        String title = input.next();
        printRecipe(recipes.findRecipe(title));
    }

    // MODIFIES: this
    // EFFECTS: enables user to add recipe
    private void doAddRecipe() {
        System.out.println("\nCreating new recipe");
        System.out.println("\nEnter new recipe title:");
        String title = input.next();
        System.out.println("Enter serving size:");
        int servings = input.nextInt();
        System.out.println("Enter ingredients with measurements:");
        System.out.println("Type 'done' when ingredient list is complete");
        ArrayList<String> ingredients = addIngredients();
        System.out.println("Enter prep time in minutes:");
        int prep = input.nextInt();
        System.out.println("Enter cook time in minutes:");
        int cook = input.nextInt();
        System.out.println("Enter directions:");
        System.out.println("Type 'done' when the list is complete");
        ArrayList<String> directions = addDirections();
        System.out.println("Enter rating:");
        int rating = input.nextInt();
        Recipe r = new Recipe(title, servings, ingredients, prep, cook, directions, rating);
        recipes.addRecipe(r);
        printRecipe(r);
    }

    // EFFECTS; prompts user to add a list of ingredients
    private ArrayList<String> addIngredients() {
        boolean keepGoing = true;
        ArrayList<String> ingredients = new ArrayList<String>();

        String ingredient = input.nextLine();

        while (keepGoing) {
            ingredients.add(ingredient);
            ingredient = input.nextLine();
            if (ingredient.equals("done")) {
                keepGoing = false;
            }
        }
        return ingredients;
    }

    // EFFECTS: prompts user to add a list of directions
    private ArrayList<String> addDirections() {
        boolean keepGoing = true;
        ArrayList<String> directions = new ArrayList<>();
        String direction = input.nextLine();

        while (keepGoing) {
            directions.add(direction);
            direction = input.nextLine();
            if (direction.equals("done")) {
                keepGoing = false;
            }
        }
        return directions;
    }

    // EFFECTS: Prints the given recipe
    private void printRecipe(Recipe r) {
        String title = r.getTitle();
        int serving = r.getServingSize();
        ArrayList<String> ingredients = r.getIngredients();
        int prepTime = r.getPrepTime();
        int cookTime = r.getCookTime();
        ArrayList<String> directions = r.getDirections();
        int rating = r.getRating();
        System.out.println("\nRecipe: " + title);
        System.out.println("\nServing size: " + serving);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Prep time: " + prepTime);
        System.out.println("Cook time: " + cookTime);
        System.out.println("Directions: " + directions);
        System.out.println("Rating: " + rating);
    }
}
