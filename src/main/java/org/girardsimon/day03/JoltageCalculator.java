package org.girardsimon.day03;

import java.util.List;

public class JoltageCalculator {

    public long computeMaxJoltage(List<BatteryBank> batteryBanks, int numberOfBatteries) {
        return batteryBanks.stream()
                .mapToLong(batteryBank -> batteryBank.computeMaxJoltage(numberOfBatteries))
                .sum();
    }
}
