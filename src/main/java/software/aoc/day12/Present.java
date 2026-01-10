package software.aoc.day12;

import java.util.List;

record Present(List<List<Character>> shape) {
    public int area() {
        return (int) shape.stream().flatMap(List::stream).filter(c -> c == '#').count();
    }
}