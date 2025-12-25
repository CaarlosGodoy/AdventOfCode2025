package software.aoc.day08.b;

import software.aoc.day08.Cable;
import software.aoc.day08.Cord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Playground {
    private List<Cord> cords;
    private final List<Cable> cables;

    public Playground() {
        this.cords = new ArrayList<>();
        this.cables = new ArrayList<>();
    }

    public long execute(String[] s) {
        cords = coords(s);
        Cable lastCable = findLastConnectingCable(sortedCables());
        return (long) lastCable.c1().x() * lastCable.c2().x();
    }

    private Cable findLastConnectingCable(List<Cable> cables) {
        List<List<Cord>> groups = groups();
        return cables.stream()
                .filter(cable -> tryConnection(groups, cable))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se pueden conectar en un circuito"));
    }

    private boolean tryConnection(List<List<Cord>> groups, Cable cable) {
        List<Cord> g1 = findGroupsHaving(groups, cable.c1());
        List<Cord> g2 = findGroupsHaving(groups, cable.c2());
        if (g1 != g2) {
            g1.addAll(g2);
            groups.remove(g2);
            return groups.size() == 1;
        }
        return false;
    }
    private List<List<Cord>> groups() {
        return cords.stream()
                .map(c -> new ArrayList<>(List.of(c)))
                .collect(Collectors.toList());
    }
    private List<Cord> findGroupsHaving(List<List<Cord>> groups, Cord c) {
        return groups.stream().filter(g -> g.contains(c)).findFirst().orElseThrow();
    }

    private List<Cable> sortedCables() {
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

    private static List<Cord> coords(String[] s) {
        return Stream.of(s).map(Playground::toCoord).toList();
    }

    private static double straightLineDistance(Cord c, Cord cord) {
        return Math.sqrt(Math.pow(c.x() - cord.x(), 2)
                + Math.pow(c.y() - cord.y(), 2)
                + Math.pow(c.z() - cord.z(), 2));
    }

    private static Cord toCoord(String s) {
        return new Cord(
                Integer.parseInt(s.split(",")[0].trim()),
                Integer.parseInt(s.split(",")[1].trim()),
                Integer.parseInt(s.split(",")[2].trim())
        );
    }
}