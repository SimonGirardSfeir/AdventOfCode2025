package org.girardsimon.day06;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public final class OperationLineParser {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{Zs}+");
    private OperationLineParser() {
    }

    public static List<OperationLine> parseOperationLines(List<String> lines) {
        String lastLine = lines.getLast();
        List<OperationType> operationTypes = Arrays.stream(WHITESPACE_PATTERN.split(lastLine.trim()))
                .map(OperationType::fromToken)
                .toList();

        Integer maxLength = lines.stream()
                .map(String::length)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid operation line: " + lastLine));

        int startIndex = 0;
        List<OperationTypeAndRange> operationTypeAndRanges = new ArrayList<>();
        char lastOperator = 'a';
        for(int i = 0; i < lastLine.length(); i++) {
            char c = lastLine.charAt(i);
            if((c == '*' || c == '+')) {
                if(i == 0) {
                    lastOperator = c;
                } else {
                    OperationType operationType = OperationType.fromToken(String.valueOf(lastOperator));
                    OperationRange operationRange = new OperationRange(startIndex, i-1);
                    OperationTypeAndRange operationTypeAndRange = new OperationTypeAndRange(operationRange, operationType);
                    operationTypeAndRanges.add(operationTypeAndRange);
                    lastOperator = c;
                    startIndex = i;
                }
            }
            if(i == lastLine.length() - 1) {
                OperationType operationType = OperationType.fromToken(String.valueOf(lastOperator));
                OperationRange operationRange = new OperationRange(startIndex, maxLength);
                OperationTypeAndRange operationTypeAndRange = new OperationTypeAndRange(operationRange, operationType);
                operationTypeAndRanges.add(operationTypeAndRange);
            }
        }

        List<String> allOperationNumbers = lines.subList(0, lines.size() - 1);

        return IntStream.range(0, operationTypeAndRanges.size())
                .mapToObj(i -> {
                    OperationTypeAndRange operationTypeAndRange = operationTypeAndRanges.get(i);
                    OperationRange operationRange = operationTypeAndRange.operationRange;
                    OperationType operationType = operationTypeAndRange.operationType;

                    List<String> numbers = allOperationNumbers.stream()
                            .map(test -> {
                                int max;
                                if(operationRange.end > test.length()) {
                                    max = test.length();
                                } else {
                                    max = operationRange.end;
                                }
                                return test.substring(operationRange.start, max);
                            })
                            .toList();
                    return new OperationLine(numbers, operationType);
                })
                .toList();
    }

    private record OperationRange(int start, int end) {
    }

    private record OperationTypeAndRange(OperationRange operationRange, OperationType operationType) {}
}
