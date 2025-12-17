package software.aoc.day09.a;

public record Coord(String[] c) {
    public int x() { return Integer.parseInt(c[0]);}
    public int y() { return Integer.parseInt(c[1]); }
}
