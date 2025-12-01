package org.girardsimon.day01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DialOrchestratorTest {

    DialOrchestrator dialOrchestrator = new DialOrchestrator();

    @Test
    void computeNumberOfTimesDialStopsToZero_should_returns_expected_result() {
        // Arrange
        List<DialInstruction> instructions = List.of(
                new DialInstruction(Rotation.LEFT, 68),
                new DialInstruction(Rotation.LEFT, 30),
                new DialInstruction(Rotation.RIGHT, 48),
                new DialInstruction(Rotation.LEFT, 5),
                new DialInstruction(Rotation.RIGHT, 60),
                new DialInstruction(Rotation.LEFT, 55),
                new DialInstruction(Rotation.LEFT, 1),
                new DialInstruction(Rotation.LEFT, 99),
                new DialInstruction(Rotation.RIGHT, 14),
                new DialInstruction(Rotation.LEFT, 82)
        );
        Dial dial = new Dial(50);

        // Act
        DialStats dialStats = dialOrchestrator.computeDialStats(dial, instructions);

        // Assert
        assertThat(dialStats.zeroStopped()).isEqualTo(3);
    }

    @Test
    void computeNumberOfTimesDialGoesThroughZero_should_returns_expected_result() {
        // Arrange
        List<DialInstruction> instructions = List.of(
                new DialInstruction(Rotation.LEFT, 68),
                new DialInstruction(Rotation.LEFT, 30),
                new DialInstruction(Rotation.RIGHT, 48),
                new DialInstruction(Rotation.LEFT, 5),
                new DialInstruction(Rotation.RIGHT, 60),
                new DialInstruction(Rotation.LEFT, 55),
                new DialInstruction(Rotation.LEFT, 1),
                new DialInstruction(Rotation.LEFT, 99),
                new DialInstruction(Rotation.RIGHT, 14),
                new DialInstruction(Rotation.LEFT, 82)
        );
        Dial dial = new Dial(50);

        // Act
        DialStats dialStats = dialOrchestrator.computeDialStats(dial, instructions);

        // Assert
        assertThat(dialStats.zeroCrossed()).isEqualTo(6);
    }

}