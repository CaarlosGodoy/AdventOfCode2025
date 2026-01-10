package software.aoc.day10.a;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Factory {
    private final Map<String, Long> mem = new HashMap<>();

    public long execute(String[] s) {
        return Arrays.stream(s)
                .mapToLong(l -> {
                    mem.clear();
                    return solve(
                            lightArray(l.split(" ")[0]),
                            btnsList(l.split(" ")),
                            0,
                            initial(lightArray(l.split(" ")[0]).size())
                    );
                }).sum();
    }

    private long solve(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        String key = index + current.toString();
        if (current.equals(goal)) return 0L;
        if (index == btns.size()) return 1000000L;
        if (mem.containsKey(key)) return mem.get(key);

        mem.put(key, Math.min(
                skipBtn(goal, btns, index + 1, current),
                takeBtn(goal, btns, index + 1, current))
        );
        return mem.get(key);
    }

    private long skipBtn(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        return solve(goal, btns, index, current);
    }

    private long takeBtn(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        return 1L + solve(goal, btns, index, next(goal, btns, index - 1, current));
    }

    private List<Long> next(List<Long> goal, List<List<Long>> btns, int index, List<Long> current) {
        return IntStream.range(0, goal.size())
                .mapToObj(i -> current.get(i) ^ btns.get(index).get(i))
                .toList();
    }

    private List<Long> initial(int size) {
        return LongStream.range(0, size).map(_ -> 0L).boxed().toList();
    }

    private List<Long> lightArray(String s) {
        return IntStream.range(0, s.length() - 2)
                .mapToObj(i -> s.charAt(i + 1) == '#' ? 1L : 0L)
                .toList();
    }

    private List<List<Long>> btnsList(String[] s) {
        return IntStream.range(1, s.length)
                .filter(i -> s[i].startsWith("("))
                .mapToObj(i -> toBinaryArray(s[i].substring(1, s[i].length() - 1).split(","), s[0].length() - 2))
                .toList();
    }
    private List<Long> toBinaryArray(String[] s, int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> Arrays.stream(s)
                        .map(String::trim)
                        .map(Long::parseLong)
                        .toList()
                        .contains((long) i) ? 1L : 0L)
                .toList();
    }
}