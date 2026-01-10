package software.aoc.day11;

public class Reactor {
    public long executeA(String[] s, String start) {
        return Graph.graph(s).countPaths(start, false, false, false);
    }

    public long executeB(String[] s, String start) {
        return Graph.graph(s).countPaths(start, false, false, true);
    }
}