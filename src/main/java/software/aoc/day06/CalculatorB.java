package software.aoc.day06;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CalculatorB implements Calculator {
    @Override
    public long calculate(String[] s) {
        return IntStream.range(0, ops(s).length)
                .mapToLong(colIdx -> getCommand(ops(s)[colIdx]).execute(
                        Arrays.stream(formatted(extractRaw(s, colIdx, ops(s)))).mapToLong(Long::parseLong)
                )).sum();
    }
    private String [] ops(String[] s) {return s[s.length - 1].trim().split("\\s+");}

    private String[] extractRaw(String[] s, int colIdx, String[] ops) {
        return Arrays.stream(s)
                .map(line -> substring(line, getStart(s, colIdx, ops), getEnd(s, colIdx, ops)))
                .toArray(String[]::new);
    }
    private String substring(String line, int start, int end) {
        return (start >= line.length()) ? "" : line.substring(start, Math.min(end, line.length()));
    }
    private int getStart(String[] s, int colIdx, String[] ops) {
        return IntStream.rangeClosed(0, getSignPos(s, colIdx, ops))
                .map(i -> getSignPos(s, colIdx, ops) - i)
                .filter(i -> i == 0 || s[s.length - 1].charAt(i - 1) == ' ')
                .findFirst()
                .orElse(0);
    }
    private int getEnd(String[] s, int colIdx, String[] ops) {
        return (colIdx < ops.length - 1)
                ? s[s.length - 1].indexOf(ops[colIdx + 1], getSignPos(s, colIdx, ops) + ops[colIdx].length())
                : Arrays.stream(s).mapToInt(String::length).max().orElse(0);
    }
    private int getSignPos(String[] s, int colIdx, String[] ops) {
        return s[s.length - 1].indexOf(ops[colIdx],
                IntStream.range(0, colIdx)
                        .reduce(0, (pos, i) -> s[s.length - 1].indexOf(ops[i], pos) + ops[i].length()));
    }


    private String[] formatted(String[] col) {
        if (maxLen(col) == 0) return new String[0];
        return IntStream.range(0, maxLen(col))
                .map(i -> (maxLen(col) - 1) - i)
                .mapToObj(i -> getNumsFrom(col, i, maxLen(col)))
                .filter(num -> !num.isEmpty())
                .toArray(String[]::new);
    }
    private String getNumsFrom(String[] col, int i, int max) {
        return Arrays.stream(col)
                .limit(col.length - 1)
                .map(row -> getCharAt(row, i, max))
                .collect(Collectors.joining())
                .trim();
    }
    private String getCharAt(String row, int i, int max) {
        return String.valueOf(paddedRow(row, max).charAt(i));
    }
    private CharSequence paddedRow(String row, int max) {
        return (row.length() < max) ? String.format("%-" + max + "s", row) : row;
    }
    private int maxLen(String[] col) {
        return Arrays.stream(col).limit(col.length - 1)
                .mapToInt(str -> str.trim().length())
                .max()
                .orElse(0);
    }
}