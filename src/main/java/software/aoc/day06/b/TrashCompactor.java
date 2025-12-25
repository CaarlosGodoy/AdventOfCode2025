package software.aoc.day06.b;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TrashCompactor {
    public static long execute(String[] s) {
        return IntStream.range(0, s[s.length - 1].trim().split("\\s+").length)
                .mapToLong(col -> operate(formatted(getCol(col, s)), getSigns(s, col)))
                .sum();
    }
    private static String[] getCol(int colIdx, String[] s) {
        String lastRow = s[s.length - 1];
        String[] ops = lastRow.trim().split("\\s+");

        int opPos = IntStream.range(0, colIdx)
                .reduce(0, (pos, i) -> lastRow.indexOf(ops[i], pos) + ops[i].length());
        int startIdx = lastRow.indexOf(ops[colIdx], opPos);

        final int fStart = IntStream.rangeClosed(0, startIdx)
                .map(i -> startIdx - i)
                .filter(i -> i == 0 || lastRow.charAt(i - 1) == ' ')
                .findFirst().orElse(0);

        final int fEnd = (colIdx < ops.length - 1)
                ? lastRow.indexOf(ops[colIdx + 1], startIdx + ops[colIdx].length())
                : Arrays.stream(s).mapToInt(String::length).max().orElse(0);

        return Arrays.stream(s)
                .map(line -> (fStart >= line.length()) ? "" : line.substring(fStart, Math.min(fEnd, line.length())))
                .toArray(String[]::new);
    }
    private static String getSigns(String[] s, int col) {
        return s[s.length - 1].trim().split("\\s+")[col];
    }

    private static String[] formatted(String[] col) {
        return nums(col, biggestIn(col)).toArray(String[]::new);
    }
    private static Stream<String> nums(String[] col, int maxLen) {
        return IntStream.range(0, maxLen)
                .map(i -> (maxLen - 1) - i)
                .mapToObj(i -> Arrays.stream(col)
                        .limit(col.length - 1)
                        .map(c -> {
                            return (c.length() < maxLen) ?
                                    String.valueOf((String.format("%-" + maxLen + "s", c)).charAt(i)) :
                                    String.valueOf(c.charAt(i));
                        })
                        .collect(Collectors.joining())
                        .trim());
    }
    private static int biggestIn(String[] col) {
        return Stream.of(col).limit(col.length - 1)
                .mapToInt(str -> str.trim().length())
                .max()
                .orElse(0);
    }

    private static long operate(String[] col, String sign) {
        return sign.equals("+") ? sumNums(col) : multNums(col);
    }
    private static long sumNums(String[] col) {
        return Arrays.stream(Arrays.stream(col)
                        .mapToLong(Long::parseLong)
                        .toArray())
                .sum();
    }
    private static long multNums(String[] col) {
        return Arrays.stream(Arrays.stream(col)
                        .mapToLong(Long::parseLong)
                        .toArray())
                .reduce(1, (a, b) -> a * b);
    }
}