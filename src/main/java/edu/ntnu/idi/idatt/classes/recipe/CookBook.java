package edu.ntnu.idi.idatt.classes.recipe;

import java.util.ArrayList;
import java.util.Optional;

public class CookBook {
  private final ArrayList<Recipe> recipes =  new ArrayList<Recipe>();

  public CookBook() {
    ArrayList<Ingredient> pancakeIngredients = new ArrayList<Ingredient>();
    pancakeIngredients.add(new Ingredient("flour", 3, "dL", true));
    pancakeIngredients.add(new Ingredient("salt", 0.5, "tsp", false));
    pancakeIngredients.add(new Ingredient("milk", 0.5, "dL", true));
    pancakeIngredients.add(new Ingredient("egg", 4, "ea", true));
    pancakeIngredients.add(new Ingredient("butter", 1, "tbsp", true));

    recipes.add(new Recipe("Desert pancakes", "Mix all, then fry.", pancakeIngredients, 4));
  }

  public ArrayList<Recipe> getRecipes() {
    return recipes;
  }

  public void addRecipes(Recipe recipe) {
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  public int searchRecipes(String searchString) {
    Optional<Recipe> searchResult = recipes.stream()
        .filter(recipe -> recipe.getName().strip().equalsIgnoreCase(searchString.strip()))
        .findFirst();

    return searchResult.map(recipes::indexOf).orElse(-1);
  }
}
