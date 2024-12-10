package edu.ntnu.idi.idatt.classes.recipe;

import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.*;

import edu.ntnu.idi.idatt.classes.foodstorage.Groceries;
import java.util.ArrayList;


public class SuggestedRecipes {
  Recipe recipe;
  ArrayList<Groceries> grocery;
  String name;

  public SuggestedRecipes(Recipe recipe, ArrayList<Groceries> grocery) {
    this.recipe = recipe;
    this.grocery = grocery;
    name = recipe.getName();
  }

  public String getName() {
    return name;
  }

  public int getPortions() {
    // Use neg 1 as infinity
    double limitingFactor = -1;
    for (Groceries groceries : grocery) {
      for (Ingredient ingredient : recipe.getIngredients()) {

        double portionsAvailable = convertToStandardUnits(groceries.totalQuantity(),
            groceries.getGroceryUnit())
            / convertToStandardUnits(ingredient.getQuantity(),
            ingredient.getUnit());

        if (portionsAvailable < limitingFactor || limitingFactor == -1) {
          limitingFactor = portionsAvailable;
        }
      }
    }
    // Truncates double instead of rounding to avoid cases where you need more
    // ingredients than you have available in the storage.
    return limitingFactor == -1 ? 0 : (int) limitingFactor;
  }

  public String recipeInfo() {
    return recipe.info(getPortions());
  }
}
