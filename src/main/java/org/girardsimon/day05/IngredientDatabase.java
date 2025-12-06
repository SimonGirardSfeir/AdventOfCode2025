package org.girardsimon.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public record IngredientDatabase(List<IngredientRange> ingredientRanges, List<Long> ingredientIds) {

    public IngredientDatabase {
        if (ingredientRanges.isEmpty()) {
            throw new IllegalArgumentException("No ingredients found");
        }
        ingredientRanges = List.copyOf(ingredientRanges);
        ingredientIds = List.copyOf(ingredientIds);
    }

    public long countNumberOfPossibleIngredientsInRanges() {
        return mergeRanges().stream()
                .mapToLong(range -> range.right() - range.left() + 1)
                .sum();
    }
    public long countNumberOfIngredientsInRange() {

        TreeMap<Long, IngredientRange> rangeTree = new TreeMap<>();

        List<IngredientRange> mergedRanges = mergeRanges();
        mergedRanges
                .forEach(ingredientRange -> rangeTree.put(ingredientRange.left(), ingredientRange));

        return ingredientIds.stream()
                .filter(id -> {
                    var entry = rangeTree.floorEntry(id);
                    if(entry == null) {
                        return false;
                    }
                    IngredientRange ingredientRange = entry.getValue();
                    return id <= ingredientRange.right();
                })
                .count();
    }

    private List<IngredientRange> mergeRanges() {
        List<IngredientRange> sortedRanges = ingredientRanges.stream()
                .map(IngredientRange::copy)
                .sorted()
                .toList();

        List<IngredientRange> mergedRanges = new ArrayList<>();
        mergedRanges.add(sortedRanges.getFirst());

        sortedRanges.stream().skip(1L).forEach(ingredientRange -> {
            IngredientRange lastMerged = mergedRanges.getLast();

            if(ingredientRange.left() <= lastMerged.right()) {
                lastMerged.setRight(Math.max(ingredientRange.right(),  lastMerged.right()));
            } else {
                mergedRanges.add(ingredientRange);
            }
        });
        return mergedRanges;
    }
}
