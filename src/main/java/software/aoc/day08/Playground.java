package software.aoc.day08;

import java.util.*;
import java.util.stream.*;

public class Playground {

    private Playground() {}

    public static Playground create() {
        return new Playground();
    }

    public long executeA(String[] s, int n) {
        List<List<Cord>> groups = groups(cords(s));

        sortedCables(cords(s)).stream()
                .limit(n)
                .toList()
                .forEach(cable -> union(groups, cable));

        return groups.stream()
                .mapToLong(List::size)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }

    public long executeB(String[] s) {
        List<List<Cord>> groups = groups(cords(s));

        Cable lastCable = sortedCables(cords(s)).stream()
                .filter(cable -> union(groups, cable) && groups.size() == 1)
                .findFirst()
                .orElseThrow();

        return (long) lastCable.c1().x() * lastCable.c2().x();
    }

    private List<List<Cord>> groups(List<Cord> cords) {
        return cords.stream()
                .map(c -> new ArrayList<>(List.of(c)))
                .collect(Collectors.toList());
    }

    private static List<Cord> cords(String[] s) {
        return Stream.of(s).map(Playground::toCord).toList();
    }

    private static Cord toCord(String s) {
        return new Cord(
                Integer.parseInt(s.split(",")[0].trim()),
                Integer.parseInt(s.split(",")[1].trim()),
                Integer.parseInt(s.split(",")[2].trim())
        );
    }

    private List<Cable> sortedCables(List<Cord> cords) {
        return IntStream.range(0, cords.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, cords.size())
                        .mapToObj(j -> new Cable(
                                cords.get(i),
                                cords.get(j),
                                straightLineDistance(cords.get(i), cords.get(j)))
                        ))
                .sorted(Comparator.comparing(Cable::distance))
                .toList();
    }
    private static double straightLineDistance(Cord c, Cord cord) {
        return Math.sqrt(Math.pow(c.x() - cord.x(), 2)
                + Math.pow(c.y() - cord.y(), 2)
                + Math.pow(c.z() - cord.z(), 2));
    }

    private boolean union(List<List<Cord>> groups, Cable cable) {
        return compareGroups(findGroupsHaving(groups, cable.c1()), findGroupsHaving(groups, cable.c2()), groups);
    }
    private boolean compareGroups(List<Cord> g1, List<Cord> g2, List<List<Cord>> groups) {
        if (g1 != g2) {
            g1.addAll(g2);
            groups.remove(g2);
            return true;
        }
        return false;
    }
    private List<Cord> findGroupsHaving(List<List<Cord>> groups, Cord c) {
        return groups.stream().filter(g -> g.contains(c)).findFirst().orElseThrow();
    }
}