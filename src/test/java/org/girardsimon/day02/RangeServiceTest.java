package org.girardsimon.day02;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RangeServiceTest {

    RangeService rangeService = new RangeService();

    @Test
    void sumBlockRepeatedTwice_should_returns_expected_result() {
        // Arrange
        List<Range> ranges = List.of(
                new Range(11L, 22L),
                new Range(95L, 115L),
                new Range(998L, 1012L),
                new Range(1188511880L, 1188511890L),
                new Range(222220L, 222224L),
                new Range(1698522L, 1698528L),
                new Range(446443L, 446449L),
                new Range(38593856L, 38593862L),
                new Range(565653L, 565659L),
                new Range(824824821L, 824824827L),
                new Range(2121212118L, 2121212124L)
        );

        // Act
        long sum = rangeService.sumSymmetricValues(ranges);

        // Assert
        assertThat(sum).isEqualTo(1227775554L);
    }

    @Test
    void sumDuplicatedValues_should_returns_expected_result() {
        // Arrange
        List<Range> ranges = List.of(
                new Range(11L, 22L),
                new Range(95L, 115L),
                new Range(998L, 1012L),
                new Range(1188511880L, 1188511890L),
                new Range(222220L, 222224L),
                new Range(1698522L, 1698528L),
                new Range(446443L, 446449L),
                new Range(38593856L, 38593862L),
                new Range(565653L, 565659L),
                new Range(824824821L, 824824827L),
                new Range(2121212118L, 2121212124L)
        );

        // Act
        long sum = rangeService.sumDuplicatedValues(ranges);

        // Assert
        assertThat(sum).isEqualTo(4174379265L);
    }
}