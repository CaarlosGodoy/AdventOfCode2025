package software.aoc.day05.a;

import java.util.Arrays;

public class Cafeteria {
    public static int execute(String s) {
        return Math.toIntExact(Arrays.stream(s.split("\n\n")[1].split("\n"))
                .map(Long::parseLong)
                .filter(id ->
                        Arrays.stream(s.split("\n\n")[0].split("\n"))
                                .anyMatch(range -> inRange(id, range))
                )
                .count());
    }

    private static boolean inRange(long id, String range) {
        return id >= Long.parseLong(range.split("-")[0]) &&
                id <= Long.parseLong(range.split("-")[1]);
    }
}
