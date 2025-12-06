package org.girardsimon.day05;

import java.util.List;
import java.util.stream.IntStream;

public final class IngredientDatabaseParser {

    private IngredientDatabaseParser() {
    }

    public static IngredientDatabase parse(List<String> lines) {
        int blankIndex = IntStream.range(0, lines.size())
                .filter(i -> lines.get(i).isBlank())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid input"));

        List<IngredientRange> ingredientRanges = lines.subList(0, blankIndex).stream()
                .map(IngredientDatabaseParser::parseIngredientRange)
                .toList();

        List<Long> ingredientIds = lines.subList(blankIndex+1, lines.size()).stream()
                .map(Long::parseLong)
                .toList();

        return new IngredientDatabase(ingredientRanges, ingredientIds);
    }

    private static IngredientRange parseIngredientRange(String line) {
        String[] splitLine = line.split("-");
        return new IngredientRange(Long.parseLong(splitLine[0]), Long.parseLong(splitLine[1]));
    }
}
