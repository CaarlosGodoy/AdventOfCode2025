package software.aoc.day11.b;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reactor {
    private Map<String, Long> mem;
    private Map<String, List<String>> graph;
    public long execute(String[] s) {
        this.mem = new HashMap<>();
        this.graph = graph(s);
        return countPaths("svr", false, false);
    }

    private long countPaths(String current, boolean dac, boolean fft) {
        if (current.equals("out")) return (dac && fft) ? 1L : 0L;
        if (mem.containsKey(key(current, dac, fft))) return mem.get(key(current, dac, fft));
        mem.put(key(current, dac, fft), value(current, dac, fft));
        return mem.get(key(current, dac, fft));
    }
    private String key(String current, boolean dac, boolean fft) {
        return current + (dac || current.equals("dac")) + (fft || current.equals("fft"));
    }
    private long value(String current, boolean dac, boolean fft) {
        return graph.getOrDefault(current, List.of()).stream()
                .mapToLong(next -> countPaths(next, dac || current.equals("dac"), fft || current.equals("fft")))
                .sum();
    }

    private static Map<String, List<String>> graph(String[] s) {
        return Stream.of(s)
                .map(l -> l.split(": "))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> {
                            return (parts.length > 1) ? Arrays.asList(parts[1].split(" ")) : Collections.emptyList();
                        }
                ));
    }
}
