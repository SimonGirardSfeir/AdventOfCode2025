package org.girardsimon.day06;

import java.util.List;

public class OperationLineCalculator {

    public long computeOperationLines(List<OperationLine> operationLines) {
        return operationLines.stream()
                .mapToLong(OperationLine::computeOperation)
                .sum();
    }

    public long computeCephalopodSumOfLines(List<OperationLine> operationLines) {
        return operationLines.stream()
                .mapToLong(OperationLine::computeCephalopodSum)
                .sum();
    }
}
