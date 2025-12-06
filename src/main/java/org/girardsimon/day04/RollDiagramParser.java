package org.girardsimon.day04;

import org.girardsimon.common.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public final class RollDiagramParser {

    public static final char PAPER_ROLL = '@';

    private RollDiagramParser() {
    }

    public static RollsDiagram parseRollsDiagram(List<String> lines) {
        Set<Position> positions = new HashSet<>();

        IntStream.range(0, lines.size())
                .forEach(i -> {
                    String line = lines.get(i);

                    IntStream.range(0, line.length())
                            .forEach(j -> {

                                char c = line.charAt(j);

                                if (c == PAPER_ROLL) {
                                    positions.add(new Position(j, i));
                                }

                            });
                });

        return new RollsDiagram(positions);
    }
}
