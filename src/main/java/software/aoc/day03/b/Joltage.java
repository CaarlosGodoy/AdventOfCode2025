package software.aoc.day03.b;

import java.util.Arrays;
import java.util.stream.Stream;

public class Joltage {
    public static long execute(String... numbers) {
        return Arrays.stream(numbers)
                .mapToLong(Joltage::findLargestComb)
                .sum();
    }

    private static long findLargestComb(String num) {
        return Stream.iterate(nextString("", num), s -> {
                    return nextString(
                            s[0] + findLargest(getString(s)),
                            s[1].substring(s[1].indexOf(findLargest(getString(s))) + 1));
                    })
                .filter(s -> s[0].length() == 12)
                .findFirst()
                .map(s -> Long.parseLong(s[0]))
                .orElseThrow();
    }

    private static String getString(String[] s) {
        return s[1].substring(0, s[1].length() - (11 - s[0].length()));
    }

    private static String[] nextString(String prev, String next) {
        return new String[]{prev, next};
    }

    private static String findLargest(String n) {
        return String.valueOf(n.chars()
                .map(Character::getNumericValue)
                .max()
                .orElseThrow());
    }
    
}
