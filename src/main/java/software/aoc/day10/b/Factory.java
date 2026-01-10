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
                    return solve(
                            joltageArray(getJoltage(l)),
                            generatePatterns(btnsList(l.split(" ")), joltageArray(getJoltage(l)).size())
                    );
                }).sum();
    }

    private long solve(List<Long> goal, Map<List<Long>, Map<List<Long>, Long>> patterns) {
        if (goal.stream().allMatch(i -> i == 0)) return 0L;
        if (mem.containsKey(goal)) return mem.get(goal);
        mem.put(goal, value(patterns, goal));
        return mem.get(goal);
    }
    private Long value(Map<List<Long>, Map<List<Long>, Long>> patterns, List<Long> goal) {
        return getBtnsFollowing(patterns, parity(goal))
                .filter(e -> IntStream.range(0, goal.size()).allMatch(i -> e.getKey().get(i) <= goal.get(i)))
                .mapToLong(e -> e.getValue() + 2 * solve(newJoltageArray(e, goal), patterns))
                .min()
                .orElse(1000000L);
    }
    private List<Long> newJoltageArray(Map.Entry<List<Long>, Long> entry, List<Long> goal) {
        return IntStream.range(0, goal.size())
                .mapToObj(i -> (goal.get(i) - entry.getKey().get(i)) / 2)
                .toList();
    }
    private Stream<Map.Entry<List<Long>, Long>> getBtnsFollowing(Map<List<Long>, Map<List<Long>, Long>> patterns, List<Long> parity) {
        return patterns.getOrDefault(parity, Map.of()).entrySet().stream();
    }
    private List<Long> parity(List<Long> goal) {
        return goal.stream().map(i1 -> i1 % 2).toList();
    }

    private Map<List<Long>, Map<List<Long>, Long>> generatePatterns(List<List<Long>> btns, int size) {
        return IntStream.range(0, combinations(btns.size()))
                .boxed()
                .collect(Collectors.groupingBy(
                        m -> getParity(m, btns, size),
                        Collectors.toMap(
                                m -> getVoltages(m, btns, size),
                                m -> countActiveButtons(m, btns.size()),
                                Math::min
                        )
                ));
    }
    private long countActiveButtons(int m, int numBtns) {
        return IntStream.range(0, numBtns)
                .filter(b -> isButtonOn(m, b))
                .count();
    }
    private boolean isButtonOn(int m, int b) {
        return (m / (int) Math.pow(2, b)) % 2 != 0;
    }
    private List<Long> getVoltages(int m, List<List<Long>> btns, int size) {
        return IntStream.range(0, size)
                .mapToObj(idx -> IntStream.range(0, btns.size())
                        .filter(b -> isButtonOn(m, b))
                        .mapToLong(b -> btns.get(b).get(idx)).sum())
                .toList();
    }
    private List<Long> getParity(int m, List<List<Long>> btns, int size) {
        return getVoltages(m, btns, size).stream()
                .map(v -> v % 2)
                .toList();
    }
    private int combinations(int size) { return (int) Math.pow(2, size); }

    private List<Long> joltageArray(String s) {
        return Stream.of(s.substring(1, s.length() - 1).split(","))
                .map(String::trim).map(Long::parseLong).toList();
    }
    private String getJoltage(String l) { return l.split(" ")[l.split(" ").length - 1]; }

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