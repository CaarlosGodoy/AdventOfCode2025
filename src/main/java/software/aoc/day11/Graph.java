package software.aoc.day11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {
    private final Map<String, List<String>> g;
    private final Map<String, Long> mem;

    private Graph(Map<String, List<String>> g) {
        this.g = g;
        this.mem = new HashMap<>();
    }

    public static Graph graph(String[] input) {
        return new Graph(
                Stream.of(input)
                        .map(l -> l.split(": "))
                        .collect(Collectors.toMap(
                                parts -> parts[0],
                                parts -> (parts.length > 1) ? Arrays.asList(parts[1].split(" ")) : Collections.emptyList()
                        ))
        );
    }

    public long countPaths(String current, boolean dac, boolean fft, boolean checkNodes) {
        if (current.equals("out")) {
            return (!checkNodes || currentDac(current, dac) && currentFft(current, fft)) ? 1L : 0L;
        }

        if (mem.containsKey(key(current, dac, fft, checkNodes))) return mem.get(key(current, dac, fft, checkNodes));
        mem.put(key(current, dac, fft, checkNodes), paths(current, dac, fft, checkNodes));
        return mem.get(key(current, dac, fft, checkNodes));
    }

    private Long paths(String current, boolean dac, boolean fft, boolean checkNodes) {
        return g.getOrDefault(current, Collections.emptyList()).stream()
                .mapToLong(next -> countPaths(next, currentDac(current, dac), currentFft(current, fft), checkNodes))
                .sum();
    }
    private String key(String current, boolean dac, boolean fft, boolean checkNodes) {
        return current + currentDac(current, dac) + currentFft(current, fft) + checkNodes;
    }
    private boolean currentFft(String current, boolean fft) {
        return fft || current.equals("fft");
    }
    private boolean currentDac(String current, boolean dac) {
        return dac || current.equals("dac");
    }
}