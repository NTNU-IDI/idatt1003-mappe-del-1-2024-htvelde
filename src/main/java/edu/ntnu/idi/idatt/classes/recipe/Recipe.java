package edu.ntnu.idi.idatt.classes.recipe;

import java.util.ArrayList;

public class Recipe {
  private String name;
  private String description;
  private ArrayList<Ingredient> ingredients;
  private int portions;

  public Recipe(String name, String description, ArrayList<Ingredient> ingredients, int portions) {
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.portions = portions;
  }


}
