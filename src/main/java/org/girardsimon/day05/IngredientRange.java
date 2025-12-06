package org.girardsimon.day05;

import java.util.Objects;

public final class IngredientRange implements Comparable<IngredientRange> {
    private final long left;
    private long right;


    public IngredientRange(long left, long right) {
        if (left > right || left < 0) {
            throw new IllegalArgumentException("Left value must be less than right value and should be greater than zero");
        }
        this.left = left;
        this.right = right;
    }

    public IngredientRange copy() {
        return new IngredientRange(this.left, this.right);
    }

    @Override
    public int compareTo(IngredientRange o) {
        return Long.compare(this.left, o.left);
    }

    public long left() {
        return left;
    }

    public long right() {
        return right;
    }

    public void setRight(long right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (IngredientRange) obj;
        return this.left == that.left &&
                this.right == that.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
