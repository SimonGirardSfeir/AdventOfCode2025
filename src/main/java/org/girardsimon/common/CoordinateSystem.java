package org.girardsimon.common;

public enum CoordinateSystem {
    STANDARD(1, 1),
    INVERTED_Y(1, -1);

    private final int xMultiplier;
    private final int yMultiplier;

    CoordinateSystem(int xMultiplier, int yMultiplier) {
        this.xMultiplier = xMultiplier;
        this.yMultiplier = yMultiplier;
    }

    public int adjustDx(int dx) {
        return dx * xMultiplier;
    }

    public int adjustDy(int dy) {
        return dy * yMultiplier;
    }
}
