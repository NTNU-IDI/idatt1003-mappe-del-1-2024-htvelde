package edu.ntnu.idi.idatt.classes.recipe;

import java.util.ArrayList;

/**
 * <h5>Class</h5>
 * <h3>Recipe</h3>
 * The recipe class, that includes only the most basic recipe handling.
 */
public class Recipe {
  private final String name;
  private final String description;
  private final ArrayList<Ingredient> ingredients;
  private final String procedure;
  private final int portions;

  /**
   * <h5>Constructor</h5>
   * <h3>Recipe</h3>
   * Constructor for the recipe.
   *
   * @param name Name of the recipe.
   * @param description Short descriptive note.
   * @param ingredients List of all needed ingredients.
   * @param procedure Details on how to make the dish.
   * @param portions Number of portions the recipe makes.
   */
  public Recipe(String name,
                String description,
                ArrayList<Ingredient> ingredients,
                String procedure,
                int portions) {
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.procedure = procedure;
    this.portions = portions;
  }

  /**
   * <h5>Method</h5>
   * <h3>info()</h3>
   * Returns a String that functions as the actual recipe.
   *
   * @return Recipe String, ready for print.
   */
  public String info() {
    return name + "\n"
        + description + "\n"
        + "Made for " + portions + " people\n"
        + ingredientsOut() + "\n\t"
        + procedure + "\n";
  }

  /**
   * <h5>Method</h5>
   * <h3>info()</h3>
   * Returns a String that functions as the actual recipe.
   * Argument modifies ingredients needed.
   *
   * @param portionsCount The number of portions a recipe can make.
   * @return Recipe String, ready for print.
   */
  public String info(double portionsCount) {
    return name + "\n"
        + description + "\n"
        + "Made for " + portionsCount + " people\n"
        + ingredientsOut(portionsCount) + "\n\t"
        + procedure + "\n";
  }

  /**
   * <h5>Method</h5>
   * <h3>getIngredients()</h3>
   * Returns all ingredients needed for the recipe.
   *
   * @return ArrayList of all ingredients required.
   */
  private String ingredientsOut() {
    StringBuilder out = new StringBuilder();
    out.append("Ingredients:\n");
    for (Ingredient i : ingredients) {
      out.append("-\t").append(i.info()).append("\n");
    }
    return out.toString();
  }

  /**
   * <h5>Method</h5>
   * <h3>getIngredients()</h3>
   * Returns all ingredients needed for the recipe as a string,
   * ready to be printed.
   *
   * @return String of all ingredients required.
   */
  private String ingredientsOut(double portionsCount) {
    StringBuilder out = new StringBuilder();
    out.append("Ingredients:\n");
    for (Ingredient i : ingredients) {
      out.append("-\t").append(i.info(portionsCount)).append("\n");
    }
    return out.toString();
  }

  /**
   * <h5>Method</h5>
   * <h3>getName()</h3>
   * Returns the name of the recipe.
   *
   * @return Name of the recipe.
   */
  public String getName() {
    return name;
  }

  /**
   * <h5>Method</h5>
   * <h3>getIngredients()</h3>
   * Returns all ingredients needed for the recipe.
   *
   * @return ArrayList of all ingredients required.
   */
  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }
}
