package org.girardsimon.day08;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day08ResolverTest {

    private static BoxPlan boxPlan;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day08/inputData.txt");
        boxPlan = BoxPlanParser.parseBoxPlan(lines);
    }

    @Test
    void resolve_part1_of_day08_problem() {
        // Act
        long multipliedCircuitSizes = boxPlan.multiplyCircuitSizes(3);

        // Assert
        assertThat(multipliedCircuitSizes).isEqualTo(57970L);
    }

    @Test
    void resolve_part2_of_day08_problem() {
        // Act
        long multipliedCircuitSizes = boxPlan.multiplyXOfTwoExtremums();

        // Assert
        assertThat(multipliedCircuitSizes).isEqualTo(8520040659L);
    }
}
