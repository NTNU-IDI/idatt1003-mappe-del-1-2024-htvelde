package edu.ntnu.idi.idatt.classes.recipe;

import java.util.ArrayList;

public class Recipe {
  private String name;
  private String description;
  private ArrayList<Ingredient> ingredients;
  private String procedure;
  private int portions;

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

  public String info() {
    return name + "\n"
        + "Made for " + portions + " people\n"
        + ingredientsOut() + "\n\t"
        + description + "\n";
  }

  private String ingredientsOut() {
    StringBuilder out = new StringBuilder();
    out.append("Ingredients:\n");
    for (Ingredient i : ingredients) {
      out.append("-\t").append(i.info()).append("\n");
    }
    return out.toString();
  }

  public String getName() {
    return name;
  }

  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }
}
