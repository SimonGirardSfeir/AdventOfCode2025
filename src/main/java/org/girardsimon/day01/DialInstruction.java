package org.girardsimon.day01;

public record DialInstruction(Rotation rotation, int angle) {
    public DialInstruction {
        if (angle < 0 || rotation == null) {
            throw new IllegalArgumentException("Invalid arguments: " + angle + ", " + rotation);
        }
    }
}
