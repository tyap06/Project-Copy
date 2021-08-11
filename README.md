# **My Personal Project**

## **A Recipe Manager Application**

**What will the application do?:**

- My application will be able to keep track of recipes that are added. Users will be able to add and delete recipes. 
  When a recipe is added, users will have an option to add more recipes or save their recipe collection. Users will also
  be able to rate the recipe based on a 5 star scale. When viewing a recipe, users can find the recipe title, 
  serving size, a list of ingredients, prep time, cook time, a list of directions to prepare the recipe, and a rating
  of the recipe.
  
  
**Who will use it?**

- This recipe manager application is catered to users that are interested in cooking and want a place to keep track of recipes.
Users can have all of their recipes stored in this app for easy access.
  
**Why is this project of interest to you?**

- I am interested in this project because I love to cook and try out new recipes. A recipe manager application will be 
  very useful for me to have all recipes stored in one place and easily accessible.
  
**User Stories**

- As a user, I want to be able to add recipes to my recipe collection.
- As a user, I want to be able to view the titles of the recipes in my collection.
- As a user, I want to be able to select a recipe and view it in detail.
- As a user, I want to be able to select a recipe and rate it on a scale of one to five stars.
- As a user, I want to be able to save my recipe collection to file.
- As a user, I want to be able to load my recipe collection from file.

Phase 4: Task 2

- I refactored my RecipeCollection class to be robust. I refactored my ViewTitles method so that when an empty list of 
  recipes is given, it will throw an IsEmptyException. RecipeCollectionTest has also been updated to include test cases
  where the expection is thrown and not thrown.

  