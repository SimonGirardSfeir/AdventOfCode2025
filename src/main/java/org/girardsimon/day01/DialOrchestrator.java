package org.girardsimon.day01;

import java.util.List;

public class DialOrchestrator {

    public DialStats computeDialStats(Dial dial, List<DialInstruction> instructions) {
        // Applies all instructions to the given dial and returns the stats
        instructions.forEach(dial::applyInstruction);
        return new DialStats(dial.getZeroStopped(), dial.getZeroCrossed());
    }
}
