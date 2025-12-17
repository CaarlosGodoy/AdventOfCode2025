package software.aoc.day08.a;

import java.util.Objects;

public record Coord(int x, int y, int z) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y && z == coord.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}