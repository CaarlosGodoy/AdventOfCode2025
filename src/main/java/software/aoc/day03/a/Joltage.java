package software.aoc.day03.a;

import java.util.Arrays;

public class Joltage {
    public static int execute(String... numbers) {
        return Arrays.stream(numbers)
                .mapToInt(Joltage::findLargest)
                .sum();
    }

    private static int findLargest(String n) {
        return Integer.parseInt(second(n, first(n.substring(0, n.length() - 1))));
    }

    private static String second(String n, String first) {
        return first + first(n.substring(n.indexOf(first)+1));
    }

    private static String first(String n) {
        return String.valueOf(n.chars()
                .map(Character::getNumericValue)
                .max()
                .orElseThrow());
    }
}
