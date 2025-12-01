package org.girardsimon.common;

public enum Direction4 {
    NORTH(0,1) , EAST(1,0), SOUTH(0,-1), WEST(-1,0);

    private final int dx;
    private final int dy;

    Direction4(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int dx(CoordinateSystem coordinateSystem) {
        return coordinateSystem.adjustDx(dx);
    }
    public int dy(CoordinateSystem coordinateSystem) {
        return coordinateSystem.adjustDy(dy);
    }

    public Direction4 turnRight() {
        return turn(1);
    }

    public Direction4 turnLeft() {
        return turn(-1);
    }

    private Direction4 turn(int direction) {
        Direction4[] values = values();
        int currentIndex = this.ordinal();
        int newIndex = (currentIndex + direction + values.length) % values.length;
        return values[newIndex];
    }
}
