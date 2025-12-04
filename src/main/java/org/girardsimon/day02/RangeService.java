package org.girardsimon.day02;

import java.util.List;

public class RangeService {

    public long sumSymmetricValues(List<Range> ranges) {
        return ranges.stream()
                .mapToLong(Range::sumBlockRepeatedTwice)
                .sum();
    }

    public long sumDuplicatedValues(List<Range> ranges) {
        return ranges.stream()
                .mapToLong(Range::sumDuplicatedValues)
                .sum();
    }
}
