package org.girardsimon.day08;

import org.girardsimon.common.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public record BoxPlan(List<Position3> boxes) {

    public long multiplyXOfTwoExtremums() {
        UnionFind unionFind = new UnionFind(boxes.size());

        List<ClosestPosition> closestPositions = getClosestPositions();

        Map<Position3, Integer> positionIndexMap = new HashMap<>();
        IntStream.range(0, boxes.size()).forEach(i -> positionIndexMap.put(boxes.get(i), i));

        ClosestPosition terminatedClosestPosition = closestPositions.getFirst();
        for(ClosestPosition closestPosition : closestPositions){
            terminatedClosestPosition = closestPosition;
            boolean areAllPositionsInSameCircuit = unionFind.union(
                    positionIndexMap.get(closestPosition.position1),
                    positionIndexMap.get(closestPosition.position2));

            if(areAllPositionsInSameCircuit){
                break;
            }
        }


        return terminatedClosestPosition.position1.x() * terminatedClosestPosition.position2.x();
    }

    public long multiplyCircuitSizes(int numberOfCircuits) {
        UnionFind unionFind = new UnionFind(boxes.size());

        List<ClosestPosition> closestPositions = getClosestPositions();

        Map<Position3, Integer> positionIndexMap = new HashMap<>();
        IntStream.range(0, boxes.size()).forEach(i -> positionIndexMap.put(boxes.get(i), i));

        IntStream.range(0, boxes.size())
                .forEach(i -> unionFind.union(
                        positionIndexMap.get(closestPositions.get(i).position1),
                        positionIndexMap.get(closestPositions.get(i).position2))
                );


        return unionFind.groupSizes().stream()
                .sorted(Comparator.reverseOrder())
                .limit(numberOfCircuits)
                .mapToLong(x -> x)
                .reduce(1L, (a, b) -> a * b);
    }

    private List<ClosestPosition> getClosestPositions() {
        List<ClosestPosition> closestPositions = new ArrayList<>();

        IntStream.range(0, boxes.size())
                        .forEach(i -> IntStream
                                .range(i+1, boxes.size()).forEach(j -> {
                                    double currentDistance = Position3.distanceBetween(boxes.get(i), boxes.get(j));
                                    closestPositions.add(new ClosestPosition(boxes.get(i), boxes.get(j), currentDistance));
                                })
                        );
        closestPositions.sort(Comparator.comparingDouble(ClosestPosition::distance));
        return closestPositions;
    }

    record ClosestPosition(Position3 position1, Position3 position2, double distance) {
        ClosestPosition {
            if (position1.compareTo(position2) > 0) {
                Position3 tmp = position1;
                position1 = position2;
                position2 = tmp;
            }
        }
    }
}
