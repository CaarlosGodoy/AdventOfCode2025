package software.aoc.day03;

import java.util.Arrays;
import java.util.stream.Stream;

public class Joltage {
    public static long execute(int n, String... numbers) {
        return Arrays.stream(numbers)
                .mapToLong(num -> findLargestComb(num, n))
                .sum();
    }

    private static long findLargestComb(String num, int n) {
        return Stream.iterate(nextString("", num), s -> nextString(
                s[0] + findLargest(getString(s, n-1)),
                s[1].substring(s[1].indexOf(findLargest(getString(s, n-1))) + 1)
        ))
                .filter(s -> s[0].length() == n)
                .findFirst()
                .map(s -> Long.parseLong(s[0]))
                .orElseThrow();
    }

    private static String findLargest(String n) {
        return String.valueOf(n.chars()
                .map(Character::getNumericValue)
                .max()
                .orElseThrow());
    }
    private static String getString(String[] s, int n) {
        return s[1].substring(0, s[1].length() - (n - s[0].length()));
    }

    private static String[] nextString(String prev, String next) {
        return new String[]{prev, next};
    }
}