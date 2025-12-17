package software.aoc.day04.b;

import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.List;
import java.util.stream.Stream;

public class PrintingDepartment {
    private List<int[]> toChange;
    public long execute(String[] s) {
        toChange = new ArrayList<>();
        return Stream.generate(() -> {
                    long n = checkPaper(s);
                    updatePaper(s);
                    return n;
                })
                .takeWhile(n -> n > 0)
                .mapToLong(Long::longValue)
                .sum();
    }
    private long checkPaper(String[] s) {
        return IntStream.range(0, s.length)
                .flatMap(i ->
                        IntStream.range(0, s[i].length())
                                .map(j -> {
                                    if (s[i].charAt(j) == '@')
                                        if (checkSurroundings(i, j, s) < 4) {
                                            toChange.add(new int[]{i, j});
                                            return 1;
                                        }
                                    return 0;
                                })
                )
                .sum();
    }
    private String modify(String[] s, int i, int j) {
        return s[i].substring(0, j) + "." + s[i].substring(j + 1);
    }
    private void updatePaper(String[] s) {
        toChange.forEach(a -> s[a[0]] = modify(s, a[0], a[1]));
    }

    private int checkSurroundings(int i, int j, String[] s) {
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
    private boolean posExists(int k, int l, int length) {
        return (k >= 0 && k < length && l >= 0 && l < length);
    }
    private boolean notCenter(int i, int j, int l, int k) {
        return k != i || l != j;
    }
}
