package org.girardsimon.day04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RollsDiagramTest {

    @Test
    void countAccessibleRollsInOneTime_InOneTime_should_return_expected_result() {
        // Arrange
        List<String> lines = List.of(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                ",@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
        );
        RollsDiagram rollsDiagram = RollDiagramParser.parseRollsDiagram(lines);

        // Act
        int numberOfAccessibleRolls = rollsDiagram.countAccessibleRollsInOneTime(4);

        // Assert
        assertThat(numberOfAccessibleRolls).isEqualTo(13);
    }

    @Test
    void countAccessibleRolls_InOneTime_should_return_expected_result() {
        // Arrange
        List<String> lines = List.of(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                ",@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
        );
        RollsDiagram rollsDiagram = RollDiagramParser.parseRollsDiagram(lines);

        // Act
        int numberOfAccessibleRolls = rollsDiagram.countAccessibleRolls(4);

        // Assert
        assertThat(numberOfAccessibleRolls).isEqualTo(43);
    }

}