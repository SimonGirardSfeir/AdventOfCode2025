package org.girardsimon.day01;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day01ResolverTest {

    private static DialStats dialStats;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day01/inputData.txt");
        List<DialInstruction> dialInstructions = InstructionsParser.parseInstructions(lines);
        DialOrchestrator dialOrchestrator = new DialOrchestrator();
        Dial dial = new Dial(50);
        dialStats = dialOrchestrator.computeDialStats(dial, dialInstructions);
    }

    @Test
    void resolve_part1_of_day01_problem() {
        // Assert
        assertThat(dialStats.zeroStopped()).isEqualTo(1120);
    }

    @Test
    void resolve_part2_of_day01_problem() {
        // Assert
        assertThat(dialStats.zeroCrossed()).isEqualTo(6554);
    }
}
