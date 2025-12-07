package org.girardsimon.day06;

import java.util.List;
import java.util.Map;

public enum OperationType {
    ADD {
        @Override
        public long apply(List<Long> numbers) {
            return numbers.stream()
                    .mapToLong(Long::longValue)
                    .sum();
        }
    }, MULTIPLY {
        @Override
        public long apply(List<Long> numbers) {
            return numbers.stream()
                    .mapToLong(Long::longValue)
                    .reduce(1L, Math::multiplyExact);
        }
    };

    private static final Map<String, OperationType> BY_TOKEN = Map.of(
            "*", MULTIPLY,
            "+", ADD
    );

    public static OperationType fromToken(String token) {
        OperationType operationType = BY_TOKEN.get(token);
        if (operationType == null) {
            throw new IllegalArgumentException("Invalid OperationType token: " + token);
        }
        return operationType;
    }

    public abstract long apply(List<Long> numbers);
}
