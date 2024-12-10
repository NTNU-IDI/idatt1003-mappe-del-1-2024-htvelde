package edu.ntnu.idi.idatt.classes.recipe;

import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.convertToStandardUnits;

import edu.ntnu.idi.idatt.classes.foodstorage.Groceries;
import java.util.ArrayList;

/**
 * <h5>Class</h5>
 * <h3>SuggestedRecipes</h3>
 * A class to collect suggested recipes with their ingredients.<br>
 * It wouldn't be farfetched to inherit from the class CookBook,
 * but that is out of the scope of this course.
 */
public class SuggestedRecipes {
  private final Recipe recipe;
  private final ArrayList<Groceries> grocery;

  /**
   * <h5>Constructor</h5>
   * <h3>SuggestedRecipes</h3>
   * Takes a recipe and a list of groceries,
   * and forms one object.
   *
   * @param recipe Recipe that is suggested.
   * @param grocery ArrayList of groceries that are used in the recipe.
   */
  public SuggestedRecipes(Recipe recipe, ArrayList<Groceries> grocery) {
    this.recipe = recipe;
    this.grocery = grocery;
  }

  /**
   * <h5>Method</h5>
   * <h3>getPortions()</h3>
   * Calculates how many portions you can make of a recipe.
   * Returns only whole portions.
   *
   * @return Portions that you can make (truncated)
   */
  public double getPortions() {
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
    return limitingFactor < 1 ? 0 : limitingFactor;
  }

  /**
   * <h5>Method</h5>
   * <h3>recipeInfo()</h3>
   * Returns a printable info string about a recipe.
   *
   * @return Recipe info.
   */
  public String recipeInfo() {
    return recipe.info(getPortions());
  }
}
