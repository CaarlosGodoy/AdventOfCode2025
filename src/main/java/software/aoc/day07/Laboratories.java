package software.aoc.day07;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Laboratories {

    public static int executeA(String[] s) {
        return (int) solve(s, ballArray(s));
    }

    private static long solve(String[] s, int[] ballArray) {
        return IntStream.rangeClosed(0, s.length - 2)
                .mapToLong(i -> IntStream.rangeClosed(0, s[0].length() - 1)
                        .filter(j -> ballArray[j] == 1)
                        .filter(j -> {
                            if (s[i + 1].charAt(j) != '^') return false;
                            modifyArray(j, ballArray);
                            return true;
                        })
                        .count()
                ).sum();
    }

    private static int[] ballArray(String[] s) {
        return IntStream.range(0, s[0].length())
                .map(i -> i == s[0].indexOf("S") ? 1 : 0)
                .toArray();
    }

    private static void modifyArray(int j, int[] ballArray) {
        ballArray[j] = 0;
        ballArray[j + 1] = 1;
        ballArray[j - 1] = 1;
    }



    public static long executeB(String[] s) {
        return LongStream.of(generateArray(s)).sum();
    }

    private static void modifyArray(int j, long count, long[] nextRow) {
        nextRow[j + 1] += count;
        nextRow[j - 1] += count;
    }

    private static long[] generateArray(String[] s) {
        return followTimelines(s, toLongArray(ballArray(s)));
    }
    private static long[] toLongArray(int[] intArray) {
        return Arrays.stream(intArray).mapToLong(i -> (long) i).toArray();
    }

    private static long[] followTimelines(String[] s, long[] ballArray) {
        IntStream.range(0, s.length - 1).forEach(i -> {
            long[] rowTemp = new long[s[0].length()];
            IntStream.range(0, s[0].length())
                    .filter(j -> ballArray[j] > 0)
                    .forEach(j -> {
                        if (s[i + 1].charAt(j) == '^') {
                            modifyArray(j, ballArray[j], rowTemp);
                        } else {
                            rowTemp[j] += ballArray[j];
                        }
                    });
            System.arraycopy(rowTemp, 0, ballArray, 0, ballArray.length);
        });
        return ballArray;
    }
}