package software.aoc.day11.a;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reactor {
    private Map<String, Integer> mem;

    public int execute(String[] s) {
        this.mem = new HashMap<>();
        return countPaths(graph(s), "you");
    }

    private int countPaths(Map<String, List<String>> graph, String current) {
        if (mem.containsKey(current)) return mem.get(current);
        if (current.equals("out")) return 1;

        int paths = graph.getOrDefault(current, Collections.emptyList()).stream()
                .mapToInt(n -> countPaths(graph, n))
                .sum();

        mem.put(current, paths);
        return paths;
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
