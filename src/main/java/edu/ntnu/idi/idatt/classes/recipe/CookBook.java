package edu.ntnu.idi.idatt.classes.recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class CookBook {
  private final ArrayList<Recipe> recipes =  new ArrayList<>();

  public CookBook() {
    ArrayList<Ingredient> pancakeIngredients = new ArrayList<>();
    pancakeIngredients.add(new Ingredient("flour", 3, "dL", true));
    pancakeIngredients.add(new Ingredient("salt", 0.5, "tsp", false));
    pancakeIngredients.add(new Ingredient("milk", 0.5, "dL", true));
    pancakeIngredients.add(new Ingredient("egg", 4, "ea", true));
    pancakeIngredients.add(new Ingredient("butter", 1, "tbsp", true));

    recipes.add(new Recipe("Desert pancakes",
        "Super easy and tasty pancakes!",
        pancakeIngredients,
        "Mix all, then fry.",
        4));

    ArrayList<Ingredient> simpleIngredients = new ArrayList<>();
    simpleIngredients.add(new Ingredient("Flour", 0.1, "tsp", false));
    simpleIngredients.add(new Ingredient("Milk", 0.1, "dL", true));
    recipes.add(new Recipe("Simple",
        "super simple",
        simpleIngredients,
        "Mix all",
        1));
  }

  public ArrayList<Recipe> getRecipes() {
    return recipes;
  }

  public void addRecipes(Recipe recipe) {
    recipes.add(recipe);
    sortRecipes();
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

  private void sortRecipes() {
    recipes.sort(Comparator.comparing(Recipe::getName));
  }
}
