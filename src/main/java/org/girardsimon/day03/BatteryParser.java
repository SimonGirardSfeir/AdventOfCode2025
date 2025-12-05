package org.girardsimon.day03;

import java.util.List;

public final class BatteryParser {

    private BatteryParser() {
    }

    public static List<BatteryBank> parseBatteryBanks(List<String> lines) {
        return lines.stream()
                .map(BatteryParser::parseBatteryBank)
                .toList();
    }

    private static BatteryBank parseBatteryBank(String line) {
        List<Integer> batteries = line.chars()
                .map(c -> c - '0')
                .boxed()
                .toList();
        return new BatteryBank(batteries);
    }
}
