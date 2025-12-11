package software.aoc.day04.a;

import java.util.stream.IntStream;

public class PrintingDepartment {
    public static long execute(String[] s) {
        return IntStream.range(0, s.length)
                .flatMap(i ->
                        IntStream.range(0, s[i].length())
                                .map(j -> {
                                    if (s[i].charAt(j) == '@')
                                        if (checkSurroundings(i, j, s) < 4)
                                            return 1;
                                    return 0;
                                })
                )
                .sum();
    }

    private static int checkSurroundings(int i, int j, String[] s) {
        return IntStream.range(i - 1, i + 2)
                .flatMap(k ->
                        IntStream.range(j - 1, j + 2)
                                .map(l -> {
                                    if (posExists(k, l, s.length) && notCenter(i, j, l, k))
                                        if (s[k].charAt(l) == '@')
                                            return 1;
                                    return 0;
                                })
                )
                .sum();
    }

    private static boolean posExists(int k, int l, int length) {
        return (k >= 0 && k < length && l >= 0 && l < length);
    }

    private static boolean notCenter(int i, int j, int l, int k) {
        return k != i || l != j;
    }
}
