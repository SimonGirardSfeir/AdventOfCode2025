package org.girardsimon.day07;

import org.girardsimon.common.CoordinateSystem;
import org.girardsimon.common.Direction4;
import org.girardsimon.common.Position;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public record ManifoldPlan(Position startingPosition, Set<Position> splitters, int height, int width) {
    public ManifoldPlan {
        if(startingPosition == null || splitters == null) {
            throw new IllegalArgumentException("Cannot construct ManifoldPlan with null starting position and splitters");
        }
    }

    public ManifoldPlan(Position startingPosition, Set<Position> splitters) {
        Set<Position> allPositions = new HashSet<>(splitters);
        allPositions.add(startingPosition);
        int width = allPositions.stream()
                .map(Position::x)
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalArgumentException("No positions found")) + 1;
        int height = allPositions.stream()
                .map(Position::y)
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalArgumentException("No positions found")) + 1;
        this(startingPosition, splitters, height, width);
    }

    public long countNumberOfSplitBeam() {
        return splitBeam(startingPosition).size();
    }

    public long countNumberOfBeamTimelines() {
        return countTimeLines(startingPosition, new HashMap<>());
    }

    public  Set<Position> splitBeam(Position position) {
        Set<Position> visitedSplitters = new HashSet<>();
        Set<Position> visitedPositions = new HashSet<>();
        Deque<Position> stack = new ArrayDeque<>();
        stack.push(position);

        while (!stack.isEmpty()) {
            Position currentPosition = stack.pop();
            if(!isInDiagram(currentPosition) || !visitedPositions.add(currentPosition)) {
                continue;
            }

            if(splitters.contains(currentPosition)) {
                visitedSplitters.add(currentPosition);
                Position right = currentPosition.fromDelta(1, 0);
                Position left = currentPosition.fromDelta(-1, 0);

                stack.push(right);
                stack.push(left);
            } else  {
                Position nextPosition = currentPosition.fromDelta(0, Direction4.SOUTH.dy(CoordinateSystem.INVERTED_Y));
                stack.push(nextPosition);
            }

        }

        return visitedSplitters;
    }

    public long countTimeLines(Position startingPosition, Map<Position, Long> memoPath) {
        Position currentPosition = startingPosition;

        while(isInDiagram(currentPosition)) {
            if(splitters.contains(currentPosition)) {

                Long cached = memoPath.get(currentPosition);
                if(cached != null) {
                    return cached;
                } else {
                    Position right = currentPosition.fromDelta(1, 0);
                    Position left = currentPosition.fromDelta(-1, 0);

                    long timelines = countTimeLines(right, memoPath) + countTimeLines(left, memoPath);
                    memoPath.put(currentPosition, timelines);

                    return timelines;
                }

            } else {
                currentPosition = currentPosition.fromDelta(0, Direction4.SOUTH.dy(CoordinateSystem.INVERTED_Y));
            }
        }

        return 1L;
    }

    private boolean isInDiagram(Position position) {
        return position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }
}
