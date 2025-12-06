package org.girardsimon.day05;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day05ResolverTest {

    private static IngredientDatabase ingredientDatabase;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day05/inputData.txt");
        ingredientDatabase = IngredientDatabaseParser.parse(lines);
    }

    @Test
    void resolve_part1_of_day05_problem() {
        // Act
        long numberOfIngredientsInRange = ingredientDatabase.countNumberOfIngredientsInRange();

        // Assert
        assertThat(numberOfIngredientsInRange).isEqualTo(635L);
    }

    @Test
    void resolve_part2_of_day05_problem() {
        // Act
        long numberOfIngredientsInRange = ingredientDatabase.countNumberOfPossibleIngredientsInRanges();

        // Assert
        assertThat(numberOfIngredientsInRange).isEqualTo(369761800782619L);
    }
}
