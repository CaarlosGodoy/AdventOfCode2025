package software.aoc.day12;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TreeFarm {
    public static int execute(String[] s) {
        return solve(shapes(s), regions(s));
    }

    private static int solve(List<List<String>> shapes, Map<String, List<List<String>>> regions) {
        return regions.entrySet().stream()
                .flatMapToInt(e -> {
                    return e.getValue().stream()
                            .mapToInt(vList -> solve(e.getKey(), getRotations(shapes, vList)));
                })
                .sum();
    }

    private static int solve(String dims, List<List<List<String>>> rotations) {
        if (!isPosible(dims, rotations)) return 0;
        if (fitsWithoutOrder(dims, rotations)) return 1;
        return 0;
    }

    private static boolean isPosible(String dims, List<List<List<String>>> rotations) {
        return area(dims) - minimumArea(rotations) >= 0;
    }
    private static int area(String dims) {
        return Integer.parseInt(dims.split("x")[0]) * Integer.parseInt(dims.split("x")[1]);
    }
    private static int minimumArea(List<List<List<String>>> rotations) {
        return rotations.stream()
                .map(List::getFirst)
                .mapToInt(TreeFarm::area)
                .sum();
    }
    private static int area(List<String> l) {
        return l.stream().mapToInt(r -> {
            return (int) r.chars()
                    .filter(ch -> ch == '#')
                    .count();
        }).sum();
    }

    private static boolean fitsWithoutOrder(String dims, List<List<List<String>>> rotations) {
        return area(dims) - 9 * rotations.size() >= 0;
    }

    private static List<List<List<String>>> getRotations(List<List<String>> shapes, List<String> v) {
        return IntStream.range(0, v.size())
                .filter(i -> !v.get(i).equals("0"))
                .boxed()
                .flatMap(i -> Stream.generate(() -> shapes.get(i)).limit(Integer.parseInt(v.get(i))))
                .map(shape -> Stream.iterate(shape, TreeFarm::rotate).limit(4).distinct().toList())
                .toList();
    }

    private static List<String> rotate(List<String> s) {
        return IntStream.range(0, s.getFirst().length())
                .mapToObj(c -> IntStream.iterate(s.size() - 1, r -> r - 1)
                        .limit(s.size())
                        .mapToObj(r -> String.valueOf(s.get(r).charAt(c)))
                        .collect(Collectors.joining()))
                .toList();
    }

    private static List<List<String>> shapes(String[] s) {
        return Stream.iterate(0, i -> (s[i].charAt(1) == ':'), i -> i + 5)
                .map(i -> toList(s, i, i+4))
                .collect(Collectors.toList());
    }
    private static List<String> toList(String[] s, int start, int end) {
        return IntStream.rangeClosed(start+1, end-1).mapToObj(i -> s[i].trim()).toList();
    }

    private static Map<String, List<List<String>>> regions(String[] s) {
        return IntStream.range(firstRegionIndex(s), s.length)
                .mapToObj(i -> s[i].split(":"))
                .filter(p -> p.length >= 2)
                .collect(Collectors.groupingBy(
                        p -> p[0].trim(),
                        Collectors.mapping(
                                p -> Arrays.asList(p[1].trim().split(" ")),
                                Collectors.toList()
                        )
                ));
    }
    private static int firstRegionIndex(String[] s) {
        return IntStream.range(0, s.length-1)
                .filter(i -> s[i].contains("x"))
                .findFirst()
                .orElse(-1);
    }
}
