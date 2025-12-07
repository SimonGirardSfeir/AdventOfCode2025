package org.girardsimon.day06;

import java.util.List;
import java.util.stream.IntStream;

public record OperationLine(List<String> numbers, OperationType operationType) {

    public OperationLine {
        if(numbers == null || operationType == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
    }

    public long computeOperation() {
        return operationType.apply(numbers.stream().map(String::trim).map(Long::parseLong).toList());
    }

    public long computeCephalopodSum() {
        if(numbers.isEmpty()) {
            return 0L;
        }
        int length = numbers.stream()
                .mapToInt(number -> number.length()-1)
                .max()
                .getAsInt();

        List<Long> numbersToCompute = IntStream.iterate(length, i -> i >= 0, i -> i - 1)
                .mapToObj(i -> {
                    List<Long> digits = numbers.stream()
                            .filter(number -> i < number.length() && Character.isDigit(number.charAt(i)))
                            .map(number -> Long.parseLong(String.valueOf(number.charAt(i))))
                            .toList();

                    return IntStream.range(0, digits.size())
                            .mapToLong(j -> digits.get(j) * Math.powExact(10L, digits.size() - j - 1))
                            .sum();
                })
                .toList();


        return operationType.apply(numbersToCompute);
    }
}
