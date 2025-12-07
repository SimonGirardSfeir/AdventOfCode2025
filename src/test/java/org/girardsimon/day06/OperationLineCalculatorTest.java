package org.girardsimon.day06;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OperationLineCalculatorTest {

    @Test
    void computeOperationLines_should_returns_expected_result() {
        // Arrange
        OperationLineCalculator operationLineCalculator = new OperationLineCalculator();
        List<String> lines = List.of(
                "123 328  51 64 ",
                " 45 64  387 23 ",
                "  6 98  215 314",
                "*   +   *   +  "
        );
        List<OperationLine> operationLines = OperationLineParser.parseOperationLines(lines);

        // Act
        long computedOperationLines = operationLineCalculator.computeOperationLines(operationLines);

        // Assert
        assertThat(computedOperationLines).isEqualTo(4277556L);
    }

    @Test
    void computeCephalopodSumOfLines_should_returns_expected_result() {
        // Arrange
        OperationLineCalculator operationLineCalculator = new OperationLineCalculator();
        List<String> lines = List.of(
                "123 328  51 64 ",
                " 45 64  387 23 ",
                "  6 98  215 314",
                "*   +   *   +  "
        );
        List<OperationLine> operationLines = OperationLineParser.parseOperationLines(lines);

        // Act
        long computedOperationLines = operationLineCalculator.computeCephalopodSumOfLines(operationLines);

        // Assert
        assertThat(computedOperationLines).isEqualTo(3263827L);
    }
}