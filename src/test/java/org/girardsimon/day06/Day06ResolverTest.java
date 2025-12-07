package org.girardsimon.day06;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day06ResolverTest {

    private static List<OperationLine> operationLines;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day06/inputData.txt");
        operationLines = OperationLineParser.parseOperationLines(lines);
    }

    @Test
    void resolve_part1_of_day06_problem() {
        // Arrange
        OperationLineCalculator operationLineCalculator = new OperationLineCalculator();

        // Act
        long computedOperationLines = operationLineCalculator.computeOperationLines(operationLines);

        // Assert
        assertThat(computedOperationLines).isEqualTo(6635273135233L);
    }

    @Test
    void resolve_part2_of_day06_problem() {
        // Arrange
        OperationLineCalculator operationLineCalculator = new OperationLineCalculator();

        // Act
        long computedOperationLines = operationLineCalculator.computeCephalopodSumOfLines(operationLines);

        // Assert
        assertThat(computedOperationLines).isEqualTo(12542543681221L);
    }
}
