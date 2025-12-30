package software.aoc.day10.b;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Factory {
    private final Map<List<Long>, Long> mem = new HashMap<>();

    public long execute(String[] s) {
        return Arrays.stream(s)
                .mapToLong(l -> {
                    mem.clear();
                    List<Long> goal = joltageArray(l.split(" ")[l.split(" ").length - 1]);
                    return solve(goal, generatePatterns(btnsList(l.split(" ")), goal.size()));
                }).sum();
    }

    private long solve(List<Long> goal, Map<List<Long>, Map<List<Long>, Long>> patterns) {
        if (goal.stream().allMatch(i -> i == 0)) return 0L;
        if (mem.containsKey(goal)) return mem.get(goal);

        List<Long> parity = goal.stream().map(i -> i % 2).toList();

        long res = patterns.getOrDefault(parity, Map.of()).entrySet().stream()
                .filter(e -> IntStream.range(0, goal.size()).allMatch(i -> e.getKey().get(i) <= goal.get(i)))
                .mapToLong(e -> e.getValue() + 2 * solve(IntStream.range(0, goal.size())
                        .mapToObj(i -> (goal.get(i) - e.getKey().get(i)) / 2).toList(), patterns))
                .min()
                .orElse(1000000L);

        mem.put(goal, res);
        return res;
    }

    private Map<List<Long>, Map<List<Long>, Long>> generatePatterns(List<List<Long>> btns, int size) {
        return IntStream.range(0, 1 << btns.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        m -> IntStream.range(0, size)
                                .mapToLong(idx -> IntStream.range(0, btns.size())
                                        .filter(b -> (m & (1 << b)) != 0)
                                        .mapToLong(b -> btns.get(b).get(idx)).sum())
                                .mapToObj(v -> v % 2).toList(),
                        Collectors.toMap(
                                m -> IntStream.range(0, size)
                                        .mapToObj(idx -> IntStream.range(0, btns.size())
                                                .filter(b -> (m & (1 << b)) != 0)
                                                .mapToLong(b -> btns.get(b).get(idx)).sum()).toList(),
                                m -> (long) Integer.bitCount(m),
                                Math::min
                        )
                ));
    }

    private List<Long> joltageArray(String s) {
        return Stream.of(s.substring(1, s.length() - 1).split(","))
                .map(String::trim).map(Long::parseLong).toList();
    }

    private List<List<Long>> btnsList(String[] s) {
        return Arrays.stream(s, 1, s.length)
                .filter(part -> part.startsWith("("))
                .map(part -> toBinaryArray(part.substring(1, part.length() - 1).split(","), s[0].length() - 2))
                .toList();
    }

    private List<Long> toBinaryArray(String[] s, int size) {
        List<Long> parsed = Arrays.stream(s).map(String::trim).map(Long::parseLong).toList();
        return IntStream.range(0, size)
                .mapToObj(i -> parsed.contains((long) i) ? 1L : 0L)
                .toList();
    }
}