package edu.ntnu.idi.idatt.utils;

import org.junit.jupiter.api.Test;

import static edu.ntnu.idi.idatt.utils.ConvertMeasurement.*;
import static org.junit.jupiter.api.Assertions.*;
class ConvertMeasurementTest {

  @Test
  void testDesiLiterToLiter() {
    double dl = 10;
    double L = deciliterToLiter(dl);
    assertEquals(1, L);
  }

  @Test
  void fiveDecilitersShouldGiveHalfLiter() {
    double dl = 5;
    double L = deciliterToLiter(dl);
    assertEquals(0.5, L);
  }

  @Test
  void zeroDeciliterShouldGiveZeroLiter() {
    double dl = 0;
    double L = deciliterToLiter(dl);
    assertEquals(0, L);
  }

  @Test
  void thousandDecilitersShouldGiveHundredLiters() {
    double dl = 1000;
    double L = deciliterToLiter(dl);
    assertEquals(100, L);
  }

  @Test
  void testLiterToDeciliter() {
    double L = 1.5;
    double dl = literToDeciliter(L);
    assertEquals(15, dl);
  }

  @Test
  void zeroLiterShouldGiveZeroDeciliter() {
    double L = 0;
    double dl = deciliterToLiter(L);
    assertEquals(0, dl);
  }

  @Test
  void hundredLitersShouldGiveThousandDeciliters() {
    double L = 100;
    double dl = literToDeciliter(L);
    assertEquals(1000, dl);
  }

  @Test
  void testGramToKiloGram() {
    double g = 100;
    double kg = gramToKilogram(g);
    assertEquals(0.1, kg);
  }

  @Test
  void zeroGramShouldGiveZeroGram() {
    double g = 0;
    double kg = gramToKilogram(g);
    assertEquals(0, kg);
  }

  @Test
  void TenThousandGramsShouldGiveTenKilograms() {
    double g = 10_000;
    double kg = gramToKilogram(g);
    assertEquals(10, kg);
  }
}