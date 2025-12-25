package software.aoc.day07.b;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Laboratories {
    public static long execute(String[] s) {
        return LongStream.of(generateArray(s)).sum();
    }

    private static long[] generateArray(String[] s) {
        long[] ballArray = new long[s[0].length()];
        ballArray[s[0].indexOf("S")] = 1;
        return followTimelines(s, ballArray);
    }

    private static long[] followTimelines(String[] s, long[] ballArray) {
        LongStream.range(0, s.length - 1).forEach(i -> {
            long[] rowTemp = new long[s[0].length()];
            IntStream.range(0, s[0].length())
                    .filter(j -> ballArray[j] > 0)
                    .forEach(j -> {
                        if (s[(int) i + 1].charAt(j) == '^') modifyArray(j, ballArray[j], rowTemp);
                        else rowTemp[j] += ballArray[j];
                    });
            System.arraycopy(rowTemp, 0, ballArray, 0, ballArray.length);
        });
        return ballArray;
    }

    private static void modifyArray(int j, long count, long[] nextRow) {
        nextRow[j + 1] += count;
        nextRow[j - 1] += count;
    }
}