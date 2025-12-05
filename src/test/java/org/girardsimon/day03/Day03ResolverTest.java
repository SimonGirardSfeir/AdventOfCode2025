package org.girardsimon.day03;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day03ResolverTest {

    private static List<BatteryBank> batteryBanks;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day03/inputData.txt");
        batteryBanks = BatteryParser.parseBatteryBanks(lines);
    }

    @Test
    void resolve_part1_of_day03_problem() {
        // Arrange
        JoltageCalculator joltageCalculator = new JoltageCalculator();

        // Act
        long maxJoltage = joltageCalculator.computeMaxJoltage(batteryBanks, 2);

        // Assert
        assertThat(maxJoltage).isEqualTo(17324L);
    }

    @Test
    void resolve_part2_of_day03_problem() {
        // Arrange
        JoltageCalculator joltageCalculator = new JoltageCalculator();

        // Act
        long maxJoltage = joltageCalculator.computeMaxJoltage(batteryBanks, 12);

        // Assert
        assertThat(maxJoltage).isEqualTo(171846613143331L);
    }
}
