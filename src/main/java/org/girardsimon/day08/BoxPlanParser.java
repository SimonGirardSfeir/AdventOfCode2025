package org.girardsimon.day08;

import java.util.List;

public final class BoxPlanParser {

    private BoxPlanParser() {
    }

    public static BoxPlan parseBoxPlan(List<String> lines) {
        List<Position3> boxes = lines.stream()
                .map(BoxPlanParser::parseBox)
                .toList();

        return new BoxPlan(boxes);
    }

    private static Position3 parseBox(String line) {
        String[] split = line.split(",");
        return new Position3(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }
}
