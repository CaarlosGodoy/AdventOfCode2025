package software.aoc.day08.a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;

public class Playground {
    private List<Coord> coords;
    private final List<Circuit> circuits;
    public Playground() {
        this.coords = new ArrayList<>();
        this.circuits = new ArrayList<>();
    }

    public long execute(String[] s, int n) {
        coords = coords(s);
        for (int v = 0; v < n; v++) {
            circuits.add(closest());
        }
        return largestCircuits();
    }

    private static List<Coord> coords(String[] s) {
        return Stream.of(s).map(Playground::toCoord).toList();
    }

    private Circuit closest() {
        return IntStream.range(0, coords.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, coords.size())
                        .mapToObj(j -> {
                            return new Circuit(coords.get(i), coords.get(j), straightLineDistance(coords.get(i), coords.get(j)));
                        }))
                .filter(c -> !circuits.contains(c))
                .min(Comparator.comparing(Circuit::distance))
                .map(connecion -> new Circuit(connecion.c1(), connecion.c2(), straightLineDistance(connecion.c1(), connecion.c2())))
                .orElseThrow();
    }

    private static double straightLineDistance(Coord c, Coord coord) {
        return Math.sqrt((c.x() - coord.x()) * (c.x() - coord.x())
                + (c.y() - coord.y()) * (c.y() - coord.y())
                + (c.z() - coord.z()) * (c.z() - coord.z()));
    }

    private static Coord toCoord(String s) {
        return new Coord(
                Integer.parseInt(s.split(",")[0]),
                Integer.parseInt(s.split(",")[1]),
                Integer.parseInt(s.split(",")[2]));
    }

    private long largestCircuits() {
        return makeConnections().stream()
                .mapToLong(List::size)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Long::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    private List<List<Coord>> makeConnections() {
        List<List<Coord>> connections = new ArrayList<>();
        System.out.println(circuits);
        circuits.forEach(c -> {
            if (!coordsInConnection(c, connections)) connections.add(toCoords(c));
        });
        System.out.println(connections);
        System.out.println(connections.toArray().length);
        return connections;
    }

    private boolean coordsInConnection(Circuit c, List<List<Coord>> connections) {
        for (List<Coord> con : connections)
            if (!checkCircuit(con, c)) return true;
        return false;
    }
    private boolean checkCircuit(List<Coord> con, Circuit c) {
        return (con.contains(c.c1()) || con.contains(c.c2()));
    }

    private static List<Coord> toCoords(Circuit c) {
        return new ArrayList<>(List.of(c.c1(), c.c2()));
    }
}
