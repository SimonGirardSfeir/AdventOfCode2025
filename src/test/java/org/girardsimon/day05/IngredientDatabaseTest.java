package org.girardsimon.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientDatabaseTest {

    @Test
    void countNumberOfIngredientsInRange_should_returns_expected_result() {
        // Arrange
        List<IngredientRange> ingredientRanges = List.of(
          new IngredientRange(3L, 5L),
          new IngredientRange(10L, 14L),
          new IngredientRange(16L, 20L),
          new IngredientRange(12L, 18L)
        );
        List<Long> ingredientsId = List.of(
          1L,
          5L,
          8L,
          11L,
          17L,
          32L
        );
        IngredientDatabase ingredientDatabase = new IngredientDatabase(ingredientRanges, ingredientsId);

        // Act
        long numberOfIngredientsInRange = ingredientDatabase.countNumberOfIngredientsInRange();

        // Assert
        assertThat(numberOfIngredientsInRange).isEqualTo(3L);
    }

    @Test
    void countNumberOfPossibleIngredientsInRanges_should_returns_expected_result() {
        // Arrange
        List<IngredientRange> ingredientRanges = List.of(
                new IngredientRange(3L, 5L),
                new IngredientRange(10L, 14L),
                new IngredientRange(16L, 20L),
                new IngredientRange(12L, 18L)
        );
        List<Long> ingredientsId = List.of(
                1L,
                5L,
                8L,
                11L,
                17L,
                32L
        );
        IngredientDatabase ingredientDatabase = new IngredientDatabase(ingredientRanges, ingredientsId);

        // Act
        long numberOfPossibleIngredientsInRanges = ingredientDatabase.countNumberOfPossibleIngredientsInRanges();

        // Assert
        assertThat(numberOfPossibleIngredientsInRanges).isEqualTo(14L);
    }

}