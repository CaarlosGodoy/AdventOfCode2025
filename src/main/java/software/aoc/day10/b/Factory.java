package software.aoc.day10.b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Factory {
    private final Map<String, Long> mem = new HashMap<>();
    public long execute(String[] s) {
        return Arrays.stream(s)
                .mapToLong(l -> {
                    mem.clear();
                    return solve(
                            joltageArray(lineLast(l)),
                            btnsList(lineBtns(l)),
                            0,
                            initial(joltageArray(lineLast(l)).size())
                    );
                })
                .sum();
    }

    private String lineLast(String l) {
        return l.split(" ")[l.split(" ").length - 1];
    }

    private String[] lineBtns(String l) {
        return l.split(" ");
    }

    private List<Long> joltageArray(String s) {
        return Stream.of(s.substring(1, s.length() - 1).split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
    }

    private List<List<Long>> btnsList(String[] s) {
        return IntStream.range(0, s.length)
                .filter(i -> s[i].startsWith("("))
                .mapToObj(i -> {
                    return toBinaryArray(
                            s[i].substring(1, s[i].length() - 1).split(","),
                            joltageArray(s[s.length - 1]).size()
                    );
                })
                .toList();
    }

    private List<Long> toBinaryArray(String[] s, int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> Stream.of(s).map(String::trim).map(Long::parseLong).anyMatch(n -> n == i) ? 1L : 0L)
                .toList();
    }

    private long solve(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        if (current.equals(goal)) return 0L;
        if (index == btns.size() || IntStream.range(0, goal.size()).anyMatch(i -> current.get(i) > goal.get(i)))
            return 1000000L;
        if (mem.containsKey(index + current.toString())) return mem.get(index + current.toString());

        long res = Math.min(
                skipBtn(goal, btns, index, current),
                takeBtn(goal, btns, index, current)
        );
        mem.put(index + current.toString(), res);
        return res;
    }

    private long skipBtn(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        return solve(goal, btns, index + 1, current);
    }

    private long takeBtn(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        return 1L + solve(goal, btns, index, next(goal, btns, index, current));
    }

    private List<Long> next(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        return IntStream.range(0, goal.size())
                .mapToObj(i -> current.get(i) + btns.get(index).get(i))
                .toList();
    }

    private List<Long> initial(int size) {
        return LongStream.range(0, size).map(i -> 0L).boxed().toList();
    }
}