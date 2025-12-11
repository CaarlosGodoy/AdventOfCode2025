package software.aoc.day03.b;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Joltage {
    public static long execute(String... numbers) {
        return Arrays.stream(numbers)
                .mapToLong(Joltage::findLargest)
                .sum();
    }

    private static long findLargest(String n) {
        return Long.parseLong(complete(n, start(n.substring(0, maxIndex(n.length())))));
    }

    private static int maxIndex(int length) {
        return length - 12;
    }


    private static String start(String n) {
        return String.valueOf(n.chars()
                .map(Character::getNumericValue)
                .max()
                .orElseThrow());
    }

    private static String complete(String n, String start) {
        return "";
    }
}
