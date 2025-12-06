package org.girardsimon.day02;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.girardsimon.common.MathUtils.countNumberOfDigits;

/**
 * Represents a closed interval of positive long values and provides
 * utility methods for computing numeric patterns within this range.
 *
 * <p>The range is inclusive: both {@code left} and {@code right}
 * are considered valid candidates.
 *
 * <p><b>Assumptions :</b>
 * <ul>
 *   <li>Both bounds of the range are strictly positive.
 *   <li>Callers are responsible for ensuring {@code left <= right}.
 * </ul>
 *
 * @param left  the lower bound (inclusive)
 * @param right the upper bound (inclusive)
 */
public record Range(long left, long right) {

    public Range {
        if (left >= right || left < 0) {
            throw new IllegalArgumentException("Left value must be less than right value and should be greater than zero");
        }
    }

    /**
     * Computes the sum of all repeated-pattern numbers within this range.
     *
     * <p>A repeated-pattern number is defined as a value constructed by taking a
     * digit block and repeating it until the target length is reached. The block
     * must divide the total number of digits exactly (e.g., block size 1 for 111,
     * block size 2 for 121212).
     *
     *
     * @return the sum of all repeated-pattern values inside the range
     */
    public long sumDuplicatedValues() {
        int leftNumberOfDigits = countNumberOfDigits(left);
        int rightNumberOfDigits = countNumberOfDigits(right);

        long patternCandidate = 1L;
        long sumDuplicated = 0L;
        Set<Long> seenValues = new HashSet<>();
        int maxNumberOfDigitsForDuplicatedValue = countNumberOfDigits(right)/2;

        while (countNumberOfDigits(patternCandidate) <= maxNumberOfDigitsForDuplicatedValue) {
            long patternCopy = patternCandidate;
            long sumCandidateForPattern = IntStream.rangeClosed(leftNumberOfDigits, rightNumberOfDigits)
                    .mapToLong(i -> computeCandidateWithDuplicatedBlocks(patternCopy, i))
                    .filter(candidate -> candidate > 0L
                            && candidate >= left
                            && candidate <= right)
                    .filter(seenValues::add)
                    .sum();
            sumDuplicated += sumCandidateForPattern;
            patternCandidate++;

        }

        return sumDuplicated;
    }

    private static long computeCandidateWithDuplicatedBlocks(long number, int length) {
        int numberOfDigits = countNumberOfDigits(number);

        if(length % numberOfDigits != 0 || numberOfDigits >= length) {
            return -1L; // cannot be a candidate
        }

        long divisor = Math.powExact(10L, numberOfDigits);
        long numerator = Math.powExact(10L, length) -1L;
        long denominator = divisor - 1;

        return number * numerator / denominator;
    }

    /**
     * Computes the sum of all double-pattern numbers within this range.
     *
     * <p>A double-pattern number is formed by concatenating a digit block with
     * itself exactly twice:
     *
     * <pre>
     *   X → XX
     *   12 → 1212
     *   987 → 987987
     * </pre>
     *
     * @return the sum of all double-pattern values inside the range
     */
    public long sumBlockRepeatedTwice() {
        int leftNumberOfDigits = countNumberOfDigits(left);

        long sumBlockRepetedTwice = 0L;

        int halfLeftNumberOfDigits = Math.floorDivExact(leftNumberOfDigits, 2);
        long candidateForDuplication;
        long computedPotentialSymmetricValue;
        int divisor = Math.powExact(10, halfLeftNumberOfDigits);
        if(leftNumberOfDigits % 2 == 1) {
            candidateForDuplication = divisor;
            computedPotentialSymmetricValue = candidateForDuplication * divisor* 10 + candidateForDuplication;
        } else {
            candidateForDuplication = Math.floorDivExact(left, divisor);
            computedPotentialSymmetricValue = candidateForDuplication * divisor + candidateForDuplication;
        }

        while (computedPotentialSymmetricValue <= right) {
            if(computedPotentialSymmetricValue >= left) {
                sumBlockRepetedTwice += computedPotentialSymmetricValue;
            }
            candidateForDuplication++;
            computedPotentialSymmetricValue = candidateForDuplication * Math.powExact(10, countNumberOfDigits(candidateForDuplication)) + candidateForDuplication;
        }

        return sumBlockRepetedTwice;
    }
}
