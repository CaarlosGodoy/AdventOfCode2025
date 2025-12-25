package software.aoc.day08;

import java.util.Objects;

public record Cord(int x, int y, int z) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cord cord = (Cord) o;
        return x == cord.x && y == cord.y && z == cord.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}