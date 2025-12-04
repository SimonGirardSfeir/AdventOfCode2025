package org.girardsimon.day02;

import org.girardsimon.LineExtractor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day02ResolverTest {

    private static List<Range> ranges;

    @BeforeAll
    static void setUp() throws IOException {
        List<String> lines = LineExtractor.getLines("src/test/resources/day02/inputData.txt");
        ranges = RangeParser.parseRanges(lines.getFirst());
    }

    @Test
    void resolve_part1_of_day02_problem() {
        // Arrange
        RangeService rangeService = new RangeService();

        // Act
        long sumInvalidIds = rangeService.sumSymmetricValues(ranges);

        // Assert
        assertThat(sumInvalidIds).isEqualTo(23560874270L);
    }

    @Test
    void resolve_part2_of_day02_problem() {
        // Arrange
        RangeService rangeService = new RangeService();

        // Act
        long sumInvalidIds = rangeService.sumDuplicatedValues(ranges);

        // Assert
        assertThat(sumInvalidIds).isEqualTo(44143124633L);
    }
}
