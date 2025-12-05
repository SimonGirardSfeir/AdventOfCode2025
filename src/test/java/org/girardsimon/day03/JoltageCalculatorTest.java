package org.girardsimon.day03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JoltageCalculatorTest {

    JoltageCalculator joltageCalculator = new JoltageCalculator();

    @Test
    void computeMaxJoltage_should_returns_expected_result() {
        // Arrange
        List<BatteryBank> batteryBanks = List.of(
                new BatteryBank(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1)),
                new BatteryBank(List.of(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9)),
                new BatteryBank(List.of(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)),
                new BatteryBank(List.of(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1))
        );

        // Act
        long maxJoltage = joltageCalculator.computeMaxJoltage(batteryBanks, 2);

        // Assert
        assertThat(maxJoltage).isEqualTo(357L);
    }

    @Test
    void computeMaxJoltage_case_12_batteries_should_returns_expected_result() {
        // Arrange
        List<BatteryBank> batteryBanks = List.of(
                new BatteryBank(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1)),
                new BatteryBank(List.of(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9)),
                new BatteryBank(List.of(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)),
                new BatteryBank(List.of(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1))
        );

        // Act
        long maxJoltage = joltageCalculator.computeMaxJoltage(batteryBanks, 12);

        // Assert
        assertThat(maxJoltage).isEqualTo(3121910778619L);
    }

}