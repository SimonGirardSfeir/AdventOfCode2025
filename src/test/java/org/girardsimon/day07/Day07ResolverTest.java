package org.girardsimon.day07;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day07ResolverTest {

    private static ManifoldPlan manifoldPlan;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day07/inputData.txt");
        manifoldPlan = ManifoldPlanParser.parse(lines);
    }

    @Test
    void resolve_part1_of_day07_problem() {
        // Act
        long numberOfSplitBeam = manifoldPlan.countNumberOfSplitBeam();

        // Assert
        assertThat(numberOfSplitBeam).isEqualTo(1533L);
    }

    @Test
    void resolve_part2_of_day07_problem() {
        // Act
        long numberOfBeamTimelines = manifoldPlan.countNumberOfBeamTimelines();

        // Assert
        assertThat(numberOfBeamTimelines).isEqualTo(10733529153890L);
    }
}
