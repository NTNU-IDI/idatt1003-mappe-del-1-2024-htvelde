package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.Classes.FoodStorage.FoodStorage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class FoodStorageTest {

  @Test
  void getStorageReturnsArrayList() {
    FoodStorage foodStorage = new FoodStorage();
    assertInstanceOf(ArrayList.class, foodStorage.getStorage());
  }

  @Test
  void searchGroceries() {
  }
}