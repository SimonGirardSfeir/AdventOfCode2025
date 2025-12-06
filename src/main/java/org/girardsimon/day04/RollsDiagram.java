package org.girardsimon.day04;

import org.girardsimon.common.CoordinateSystem;
import org.girardsimon.common.Direction8;
import org.girardsimon.common.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record RollsDiagram(Set<Position> paperRolls, int width, int height) {

    private static final Direction8[] ALL_DIRECTIONS = Direction8.values();

    public RollsDiagram {
        if(width < 0 || height < 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        paperRolls = Set.copyOf(paperRolls);
    }

    public RollsDiagram(Set<Position> paperRolls) {
        int width = paperRolls.stream()
                .map(Position::x)
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalArgumentException("No positions found")) + 1;
        int height = paperRolls.stream()
                .map(Position::y)
                .max(Integer::compare)
                .orElseThrow(() -> new IllegalArgumentException("No positions found")) + 1;
        this(paperRolls, width, height);
    }

    public int countAccessibleRolls(int maxNeighbors) {
        if (maxNeighbors < 0) {
            throw new IllegalArgumentException("Max neighbors must be positive");
        }
        Set<Position> papers = new HashSet<>(paperRolls);
        Set<Position> allAccessibleRolls = new HashSet<>();
        Set<Position> currentAccessiblePaperRolls = allAccessibleRolls(maxNeighbors, papers);

        while (!currentAccessiblePaperRolls.isEmpty()) {
            allAccessibleRolls.addAll(currentAccessiblePaperRolls);
            papers.removeAll(currentAccessiblePaperRolls);
            currentAccessiblePaperRolls = allAccessibleRolls(maxNeighbors, papers);
        }
        return allAccessibleRolls.size();
    }

    public int countAccessibleRollsInOneTime(int maxNeighbors) {
        if (maxNeighbors < 0) {
            throw new IllegalArgumentException("Max neighbors must be positive");
        }
        return allAccessibleRolls(maxNeighbors, new HashSet<>(paperRolls))
                .size();
    }

    private Set<Position> allAccessibleRolls(int maxNeighbors, Set<Position> papers) {
        return papers.stream()
                .filter(paperRoll -> hasFewerNeighborsThan(maxNeighbors, papers, paperRoll))
                .collect(Collectors.toSet());
    }

    private boolean hasFewerNeighborsThan(int maxNeighbors, Set<Position> papers, Position paperRoll) {
        int count =  0;
        for(Direction8 direction : ALL_DIRECTIONS) {
            Position position = paperRoll.fromDelta(direction.dx(CoordinateSystem.INVERTED_Y),
                    direction.dy(CoordinateSystem.INVERTED_Y));
            if(!isInDiagram(position) || !papers.contains(position)) {
                continue;
            } else {
                count++;
            }
            if(count >= maxNeighbors) {
                return false;
            }
        }
        return true;
    }

    private boolean isInDiagram(Position position) {
        return position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }
}
