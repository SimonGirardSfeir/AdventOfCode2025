package org.girardsimon.day08;

public record Position3(long x, long y, long z) implements Comparable<Position3> {

    public static double distanceBetween(Position3 p1, Position3 p2){
        return Math.sqrt(Math.powExact(p1.x - p2.x, 2) + Math.powExact(p1.y - p2.y, 2) + (double) Math.powExact(p1.z - p2.z, 2));
    }

    @Override
    public int compareTo(Position3 o) {
        return Long.compare(x, o.x);
    }
}
