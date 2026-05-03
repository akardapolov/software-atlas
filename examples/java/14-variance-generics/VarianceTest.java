package com.example.springsecurity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests demonstrating Variance concepts in Java Generics
 *
 * Invariance    — List<T>              — read ✅ write ✅ (exact type only)
 * Covariance    — List<? extends T>   — read ✅ write ❌ (Producer)
 * Contravariance — List<? super T>    — read ⚠️ write ✅ (Consumer)
 */
class VarianceTest {

  // =========================================================
  // INVARIANCE — List<T>
  // Box<Cat> is NOT a Box<Animal>
  // =========================================================

  @Test
  @DisplayName("Invariance #1 — exact type: List<Integer> only accepts Integer")
  void invariance_exactType() {
    List<Integer> list = new ArrayList<>();

    // ✅ WRITE: only exact type Integer allowed
    list.add(1);
    list.add(2);
    list.add(3);

    // ✅ READ: always get Integer
    Integer first = list.get(0);
    assertEquals(1, first);

    // ❌ DOES NOT COMPILE — uncomment to see compiler error:
    // List<Number> numbers = list;   // error: incompatible types
    // List<Object> objects = list;   // error: incompatible types
  }

  @Test
  @DisplayName("Invariance #2 — List<Number> accepts Number and subtypes at write")
  void invariance_numberList() {
    List<Number> list = new ArrayList<>();

    // ✅ WRITE: Number and any subtype can be added
    list.add(42);        // Integer
    list.add(3.14);      // Double
    list.add(100L);      // Long
    list.add(1.5f);      // Float

    // ✅ READ: always get Number
    Number first = list.get(0);
    assertEquals(42, first.intValue());

    // ❌ DOES NOT COMPILE — uncomment to see compiler error:
    // List<Integer> integers = list;  // error: incompatible types
    // List<Object>  objects  = list;  // error: incompatible types
  }

  @Test
  @DisplayName("Invariance #3 — two different generic types are never compatible")
  void invariance_noRelationBetweenTypes() {
    List<Integer> integers = new ArrayList<>(List.of(1, 2, 3));
    List<Number>  numbers  = new ArrayList<>(List.of(1.1, 2.2, 3.3));

    // ✅ Each list works independently with its own type
    integers.add(4);
    numbers.add(4.4);

    assertEquals(4, integers.size());
    assertEquals(4, numbers.size());

    // ✅ READ: each returns its own type
    Integer i = integers.get(0);
    Number  n = numbers.get(0);

    assertEquals(1, i);
    assertEquals(1.1, n.doubleValue(), 0.001);

    // ❌ DOES NOT COMPILE — uncomment to see compiler error:
    // integers = numbers;   // error: incompatible types
    // numbers  = integers;  // error: incompatible types
  }


  // =========================================================
  // COVARIANCE — List<? extends T>
  // Producer<Cat> IS-A Producer<Animal>
  // READ only — unknown which exact subtype is inside
  // =========================================================

  @Test
  @DisplayName("Covariance #1 — List<? extends Number> accepts Integer, Double, Number lists")
  void covariance_acceptsSubtypes() {
    List<Integer> integers = new ArrayList<>(List.of(1, 2, 3));
    List<Double>  doubles  = new ArrayList<>(List.of(1.1, 2.2, 3.3));
    List<Number>  numbers  = new ArrayList<>(List.of(100, 200));

    // ✅ All subtypes of Number are accepted
    List<? extends Number> fromIntegers = integers;
    List<? extends Number> fromDoubles  = doubles;
    List<? extends Number> fromNumbers  = numbers;

    // ✅ READ: always safe — we know it's at least Number
    Number n1 = fromIntegers.get(0);
    Number n2 = fromDoubles.get(0);
    Number n3 = fromNumbers.get(0);

    assertEquals(1,   n1.intValue());
    assertEquals(1.1, n2.doubleValue(), 0.001);
    assertEquals(100, n3.intValue());
  }

  @Test
  @DisplayName("Covariance #2 — can read as Number, cannot read as Integer")
  void covariance_readAsUpperBound() {
    List<Integer> integers = new ArrayList<>(List.of(10, 20, 30));
    List<? extends Number> list = integers;

    // ✅ READ as Number — always safe
    Number n = list.get(0);
    assertEquals(10, n.intValue());

    // ✅ READ as Object — always safe
    Object o = list.get(0);
    assertNotNull(o);

    // ❌ DOES NOT COMPILE — uncomment to see compiler error:
    // Integer i = list.get(0);  // error: incompatible types
    //                           // might be Double, not Integer!

    // ❌ WRITE is forbidden — uncomment to see compiler error:
    // list.add(42);    // error: no suitable method found
    // list.add(3.14);  // error: no suitable method found
    // list.add(null);  // only null is allowed (useless in practice)
  }

  @Test
  @DisplayName("Covariance #3 — real use case: sum of any Number list")
  void covariance_sumProducer() {
    List<Integer> integers = List.of(1, 2, 3);
    List<Double>  doubles  = List.of(1.5, 2.5, 3.5);
    List<Long>    longs    = List.of(10L, 20L, 30L);

    // Same method works for all subtypes of Number
    assertEquals(6.0,  sum(integers), 0.001);
    assertEquals(7.5,  sum(doubles),  0.001);
    assertEquals(60.0, sum(longs),    0.001);
  }

  // ✅ Producer — only reads from the list
  private double sum(List<? extends Number> list) {
    double result = 0;
    for (Number n : list) {   // READ ✅
      result += n.doubleValue();
    }
    // list.add(42);  // ❌ DOES NOT COMPILE — cannot write
    return result;
  }


  // =========================================================
  // CONTRAVARIANCE — List<? super T>
  // Consumer<Animal> IS-A Consumer<Cat>
  // WRITE only — unknown which exact supertype is inside
  // =========================================================

  @Test
  @DisplayName("Contravariance #1 — List<? super Integer> accepts Integer, Number, Object lists")
  void contravariance_acceptsSupertypes() {
    List<Integer> integers = new ArrayList<>();
    List<Number>  numbers  = new ArrayList<>();
    List<Object>  objects  = new ArrayList<>();

    // ✅ All supertypes of Integer are accepted
    List<? super Integer> intoIntegers = integers;
    List<? super Integer> intoNumbers  = numbers;
    List<? super Integer> intoObjects  = objects;

    // ✅ WRITE: Integer fits into Integer, Number and Object
    intoIntegers.add(1);
    intoNumbers.add(2);
    intoObjects.add(3);

    assertEquals(1, integers.get(0));
    assertEquals(2, numbers.get(0));
    assertEquals(3, objects.get(0));
  }

  @Test
  @DisplayName("Contravariance #2 — can only read as Object, not as Integer or Number")
  void contravariance_readOnlyAsObject() {
    List<Number> numbers = new ArrayList<>(List.of(1, 2.5, 3L));
    List<? super Integer> list = numbers;

    // ✅ WRITE: Integer always fits into supertype
    list.add(42);
    list.add(100);

    // ✅ READ only as Object — that's all compiler can guarantee
    Object o = list.get(0);
    assertNotNull(o);

    // ❌ DOES NOT COMPILE — uncomment to see compiler error:
    // Integer i = list.get(0);  // error: incompatible types
    //                           // might be Object, not Integer!
    // Number  n = list.get(0);  // error: incompatible types
    //                           // might be Object, not Number!
  }

  @Test
  @DisplayName("Contravariance #3 — real use case: fill any supertype list with integers")
  void contravariance_fillConsumer() {
    List<Integer> integers = new ArrayList<>();
    List<Number>  numbers  = new ArrayList<>();
    List<Object>  objects  = new ArrayList<>();

    // Same method works for all supertypes of Integer
    fill(integers);
    fill(numbers);
    fill(objects);

    assertEquals(List.of(1, 2, 3), integers);
    assertEquals(3, numbers.size());
    assertEquals(3, objects.size());
  }

  // ✅ Consumer — only writes into the list
  private void fill(List<? super Integer> list) {
    list.add(1);   // WRITE ✅
    list.add(2);   // WRITE ✅
    list.add(3);   // WRITE ✅

    // Object o = list.get(0);   // ✅ compiles but useless
    // Integer i = list.get(0);  // ❌ DOES NOT COMPILE — cannot read as Integer
  }
}