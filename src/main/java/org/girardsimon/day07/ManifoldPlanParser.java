package org.girardsimon.day07;

import org.girardsimon.common.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public final class ManifoldPlanParser {

    private ManifoldPlanParser() {
    }

    public static ManifoldPlan parse(List<String> lines) {
        AtomicReference<Position> startingPositionReference = new  AtomicReference<>();
        Set<Position> splitters = new HashSet<>();
        IntStream.range(0, lines.size())
                .forEach(i -> IntStream.range(0, lines.get(i).length())
                        .forEach(j -> {
                            if(lines.get(i).charAt(j) == 'S') {
                                startingPositionReference.set(new Position(j, i));
                            }
                            if(lines.get(i).charAt(j) == '^') {
                                splitters.add(new Position(j, i));
                            }
                        }));
        return new ManifoldPlan(startingPositionReference.get(), splitters);
    }
}
