package edu.ntnu.idi.idatt.classes.recipe;

import edu.ntnu.idi.idatt.classes.foodstorage.Groceries;

import java.util.ArrayList;

import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.*;

public class SuggestedRecipes {
  Recipe recipe;
  ArrayList<Groceries> grocery;

  public SuggestedRecipes(Recipe recipe, ArrayList<Groceries> grocery) {
    this.recipe = recipe;
    this.grocery = grocery;
  }

  private double translateToStandardUnits(double quantity, String unit) {
    if (unit.matches("g|gram|grams")) {
      return gramToKilogram(quantity);
    }
    if (unit.matches("kg|kilogram|kilograms")) {
      return quantity;
    }
    if (unit.matches("[dD][lL]|desiliter|deciliter")) {
      return deciliterToLiter(quantity);
    }
    if (unit.matches("L|l|liter|Liter|liters")) {
      return quantity;
    }
    if (unit.matches("[mM]+|milliliter")) {
      return milliliterToLiter(quantity);
    }
    if (unit.matches("[tT][bB][sS][pP]|tablespoon|Tablespoon")) {
      return tablespoonToLiter(quantity);
    }
    if (unit.matches("[tT][sS][pP]|teaspoon|Teaspoon")) {
      return teaspoonToLiter(quantity);
    }
    return quantity;
  }

  public int getPortions() {
    // Use neg 1 as infinity
    double limitingFactor = -1;
    for (Groceries groceries : grocery) {
      for (Ingredient ingredient : recipe.getIngredients()) {

        double portionsAvailable = translateToStandardUnits(groceries.totalQuantity(),
            groceries.getGroceryUnit())
            / translateToStandardUnits(ingredient.getQuantity(),
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

  public String printRecipes() {
    return "";
  }
}
