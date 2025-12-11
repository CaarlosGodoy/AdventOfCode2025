package software.aoc.day06.b;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TrashCompactor {

    public static long execute(String[] s) {
        return IntStream.rangeClosed(0, s[0].split("\\s+").length - 1)
                        .mapToLong(col -> operate(getCol(col, s)))
                        .sum();
    }

    private static String[] getCol(int col, String[] s) {
        return Arrays.stream(s).map(l -> l.split("\\s+")[col]).toArray(String[]::new);
    }

    private static long operate(String[] column) {
        System.out.println(Arrays.toString(column));
        if (isSum(column[column.length-1]))
            return Stream.of(column)
                    .limit(column.length-1)
                    .mapToLong(Long::parseLong)
                    .sum();
        return Stream.of(column)
                .limit(column.length-1)
                .mapToLong(Long::parseLong)
                .reduce(1, (a, b) -> a * b);
    }

    private static boolean isSum(String s) {
        return s.equals("+");
    }
}
