package org.girardsimon.day01;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InstructionsParser {

    private static final Pattern DIAL_INSTRUCTION_PATTERN = Pattern.compile("^([LR])(\\d+)$");

    private InstructionsParser() {
    }

    public static List<DialInstruction> parseInstructions(List<String> lines) {
        return lines.stream()
                .map(InstructionsParser::parseLine)
                .toList();
    }

    private static DialInstruction parseLine(String line) {
        Matcher matcher = DIAL_INSTRUCTION_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid line format: '" + line + "'");
        }
        String rotationToken = matcher.group(1);
        Rotation rotation = Rotation.fromToken(rotationToken);

        String angleToken = matcher.group(2);

        int value = Integer.parseInt(angleToken);
        return new DialInstruction(rotation, value);
    }
}
