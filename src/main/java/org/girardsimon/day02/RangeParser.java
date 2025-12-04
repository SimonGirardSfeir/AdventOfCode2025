package org.girardsimon.day02;

import java.util.Arrays;
import java.util.List;

public final class RangeParser {

    private RangeParser() {
    }

    public static List<Range> parseRanges(String line) {
        return Arrays.stream(line.split(","))
                .map(RangeParser::mapLineToRange)
                .toList();
    }

    private static Range mapLineToRange(String splitLine) {
        String[] rangeValues = splitLine.split("-");
        return new Range(Long.parseLong(rangeValues[0]), Long.parseLong(rangeValues[1]));
    }

}
