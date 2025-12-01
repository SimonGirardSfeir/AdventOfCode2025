package org.girardsimon.day01;

/**
 * Represents a circular dial with 100 discrete positions (0 to 99).
 * <p>
 * The dial is mutable: applying instructions rotates the dial and updates two counters:
 * <ul>
 *   <li><b>zeroStopped</b> — how many times the dial ends at position 0 after an instruction</li>
 *   <li><b>zeroCrossed</b> — how many times a rotation passes through position 0</li>
 * </ul>
 *
 * Left rotations decrease the position, right rotations increase it.
 * Position values wrap around using modulo arithmetic.
 *
 */
public class Dial {

    private static final int DIAL_POSITIONS_SIZE = 100;

    private int position;
    private int zeroStopped = 0;
    private int zeroCrossed = 0;

    public Dial(int startingPosition) {
        if (startingPosition < 0 || startingPosition >= DIAL_POSITIONS_SIZE) {
            throw new IllegalArgumentException("Invalid starting position: " + startingPosition);
        }
        this.position = startingPosition;
    }

    @SuppressWarnings("java:S1301") // Switch statement is more readable than if-else for enums
    public void applyInstruction(DialInstruction dialInstruction) {
        switch (dialInstruction.rotation()) {
            case LEFT -> applyLeftRotation(dialInstruction.angle());
            case RIGHT -> applyRightRotation(dialInstruction.angle());
        }
        if(position == 0) {
            zeroStopped++;
        }
    }

    private void applyLeftRotation(int angle) {
        int next = position - angle;
        /*
         * If this starting position is 0, we should not count this position as a zero crossing.
         */
        zeroCrossed += position != 0 ?
                Math.abs((next - DIAL_POSITIONS_SIZE) / DIAL_POSITIONS_SIZE)
                : angle / DIAL_POSITIONS_SIZE;
        position = Math.floorMod(next, DIAL_POSITIONS_SIZE);
    }

    private void applyRightRotation(int angle) {
        int next = position + angle;
        zeroCrossed += next / DIAL_POSITIONS_SIZE;
        position = Math.floorMod(next, DIAL_POSITIONS_SIZE);
    }

    public int getZeroStopped() {
        return zeroStopped;
    }

    public int getZeroCrossed() {
        return zeroCrossed;
    }
}
