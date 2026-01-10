package software.aoc.day06;

import java.util.Arrays;
import java.util.stream.IntStream;

class CalculatorA implements Calculator {
    @Override
    public long calculate(String[] s) {
        return IntStream.range(0, s[0].trim().split("\\s+").length)
                .mapToLong(col -> getCommand(getData(s, col)[getData(s, col).length - 1])
                        .execute(Arrays.stream(getData(s, col))
                            .limit(getData(s, col).length - 1)
                            .mapToLong(Long::parseLong)
                        )).sum();
    }

    private static String[] getData(String[] s, int col) {
        return Arrays.stream(s).map(l -> l.trim().split("\\s+")[col]).toArray(String[]::new);
    }
}
