package software.aoc.day12;

import java.util.*;
import java.util.stream.*;

public class TreeFarm {
    private final Charge charge;
    public TreeFarm(Charge charge) { this.charge = charge; }

    public static int execute(String[] lines) {
        return new TreeFarm(toCharge(lines)).solve();
    }

    private static Charge toCharge(String[] lines) {
        return new Charge(presents(toList(lines)), regions(toList(lines)));
    }

    private static List<Present> presents(List<String> input) {
        return IntStream.range(0, input.size())
                .filter(i -> input.get(i).endsWith(":"))
                .mapToObj(i -> new Present(input.subList(i + 1, input.size()).stream()
                        .takeWhile(s -> !s.contains(":") && !s.contains("x"))
                        .map(s -> s.chars().mapToObj(c -> (char) c).toList())
                        .toList()))
                .toList();
    }
    private static List<Region> regions(List<String> input) {
        return input.stream()
                .filter(s -> s.contains("x"))
                .map(s -> new Region(
                        Integer.parseInt(s.split("x")[0]),
                        Integer.parseInt(s.split("x")[1].split(":")[0].trim()),
                        Arrays.stream(s.split(":")[1].trim().split("\\s+")).map(Integer::parseInt).toList()))
                .toList();
    }
    private static List<String> toList(String[] lines) {
        return Arrays.stream(lines).map(String::trim).filter(s -> !s.isEmpty()).toList();
    }

    public int solve() {
        return (int) charge.regions().stream()
                .filter(this::fits)
                .count();
    }
    private boolean fits(Region r) {
        return r.area() >= IntStream.range(0, r.requestPresent().size())
                .map(i -> r.requestPresent().get(i) * charge.forms().get(i).area())
                .sum();
    }
}