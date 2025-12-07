package org.girardsimon.day06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OperationLineParser {

    private OperationLineParser() {
    }

    public static List<OperationLine> parseOperationLines(List<String> lines) {
        String lastLine = lines.getLast();

        Integer maxLength = lines.stream()
                .map(String::length).max(Comparator.naturalOrder())
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

        return operationTypeAndRanges.stream().map(typeAndRange -> {
                    OperationRange operationRange = typeAndRange.operationRange;

                    List<String> numbers = allOperationNumbers.stream()
                            .map(test -> {
                                int max = Math.min(operationRange.end, test.length());
                                return test.substring(operationRange.start, max);
                            })
                            .toList();
                    OperationType operationType = typeAndRange.operationType;
                    return new OperationLine(numbers, operationType);
                })
                .toList();
    }

    private record OperationRange(int start, int end) {
    }

    private record OperationTypeAndRange(OperationRange operationRange, OperationType operationType) {}
}
