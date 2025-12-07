package org.girardsimon.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManifoldPlanTest {

    @Test
    void countNumberOfSplitBeam_should_return_expected_result() {
        // Arrange
        List<String> pattern = List.of(
                ".......S.......",
                "...............",
                ".......^.......",
                "...............",
                "......^.^......",
                "...............",
                ".....^.^.^.....",
                "...............",
                "....^.^...^....",
                "...............",
                "...^.^...^.^...",
                "...............",
                "..^...^.....^..",
                "...............",
                ".^.^.^.^.^...^.",
                "..............."
        );
        ManifoldPlan manifoldPlan = ManifoldPlanParser.parse(pattern);

        // Act
        long numberOfSplit = manifoldPlan.countNumberOfSplitBeam();

        // Assert
        assertThat(numberOfSplit).isEqualTo(21L);
    }

    @Test
    void countNumberOfBeamTimelines_should_return_expected_result() {
        // Arrange
        List<String> pattern = List.of(
                ".......S.......",
                "...............",
                ".......^.......",
                "...............",
                "......^.^......",
                "...............",
                ".....^.^.^.....",
                "...............",
                "....^.^...^....",
                "...............",
                "...^.^...^.^...",
                "...............",
                "..^...^.....^..",
                "...............",
                ".^.^.^.^.^...^.",
                "..............."
        );
        ManifoldPlan manifoldPlan = ManifoldPlanParser.parse(pattern);

        // Act
        long numberOfSplit = manifoldPlan.countNumberOfBeamTimelines();

        // Assert
        assertThat(numberOfSplit).isEqualTo(40L);
    }

}