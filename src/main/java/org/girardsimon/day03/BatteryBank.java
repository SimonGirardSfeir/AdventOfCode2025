package org.girardsimon.day03;

import java.util.List;

public record BatteryBank(List<Integer> batteries) {

    public long computeMaxJoltage(int numberOfBatteries) {

        int size = batteries.size();

        int startIndex = 0;
        long maxJoltage = 0L;
        for (int i = numberOfBatteries; i > 0; i--) {
            int maxValue = Integer.MIN_VALUE;
            for(int j = startIndex; j <= size - i; j++) {
                if(batteries.get(j) > maxValue) {
                    startIndex = j+1;
                    maxValue = batteries.get(j);
                }
            }
            maxJoltage += maxValue * Math.powExact(10L, i-1);
        }

        return maxJoltage;
    }
}
