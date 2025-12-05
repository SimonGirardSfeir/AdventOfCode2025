package org.girardsimon.common;

public final class MathUtils {
    private MathUtils() {}

    public static int countNumberOfDigits(long number) {
        return (int) Math.floor(Math.log10(number) + 1);
    }
}
