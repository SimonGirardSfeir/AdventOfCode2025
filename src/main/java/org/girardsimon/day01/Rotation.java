package org.girardsimon.day01;

import java.util.Map;

public enum Rotation {
    LEFT, RIGHT;

    private static final Map<String, Rotation> BY_TOKEN = Map.of(
            "L", LEFT,
            "R", RIGHT
    );

    public static Rotation fromToken(String token) {
        Rotation rotation = BY_TOKEN.get(token);
        if (rotation == null) {
            throw new IllegalArgumentException("Invalid rotation token: " + token);
        }
        return rotation;
    }
}
