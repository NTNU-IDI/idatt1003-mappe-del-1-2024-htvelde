package edu.ntnu.idi.idatt.classes.recipe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

/**
 * <h5>Class</h5>
 * <h3>CookBook</h3>
 * A class with information on recipes.
 */
public class CookBook {
  private final ArrayList<Recipe> recipes =  new ArrayList<>();

  /**
   * <h5>Constructor</h5>
   * <h3>CookBook</h3>
   * Creating some default recipes, when creating a new cook book.
   */
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

  /**
   * <h5>Method</h5>
   * <h3>getRecipes()</h3>
   * Gives all the recipes in an ArrayList.
   *
   * @return Returns an ArrayList of all recipes.
   */
  public ArrayList<Recipe> getRecipes() {
    return recipes;
  }

  /**
   * <h5>Method</h5>
   * <h3>addRecipe()</h3>
   * Adds a recipe to the the recipes list.
   *
   * @param recipe Recipe to be added.
   */
  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
    sortRecipes();
  }

  /**
   * <h5>Method</h5>
   * <h3>removeRecipe()</h3>
   * Removes a recipe from the the recipes list.
   *
   * @param recipe Recipe to be removed.
   */
  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  /**
   * <h5>Method</h5>
   * <h3>searchRecipesIndex()</h3>
   * Searches for recipe with a given name.
   * Returns index. Should be careful not to mutate list
   * between searching and using the index.
   *
   * @param searchString Name to search for.
   * @return Index for the recipe or -1.
   */
  public int searchRecipesIndex(String searchString) {
    Optional<Recipe> searchResult = recipes.stream()
        .filter(recipe -> recipe.getName().strip().equalsIgnoreCase(searchString.strip()))
        .findFirst();

    return searchResult.map(recipes::indexOf).orElse(-1);
  }

  /**
   * <h5>Method</h5>
   * <h3>searchRecipesIndex()</h3>
   * Searches for recipe with a given name.
   * Returns index. Should be careful not to mutate list
   * between searching and using the index.
   *
   * @param searchString Name to search for.
   * @return Index for the recipe or -1.
   */
  public Recipe searchRecipes(String searchString) {
    Optional<Recipe> out = recipes.stream()
        .filter(r -> r.getName().equalsIgnoreCase(searchString))
        .findFirst();

    return out.orElse(null);
  }

  /**
   * <h5>Method</h5>
   * <h3>sortRecipes()</h3>
   * Mutates the recipes list to be sorted after name.
   */
  private void sortRecipes() {
    recipes.sort(Comparator.comparing(Recipe::getName));
  }
}
