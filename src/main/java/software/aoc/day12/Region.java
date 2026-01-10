package software.aoc.day12;

import java.util.List;

record Region(int width, int height, List<Integer> requestPresent) {
    public int area() {
        return width * height;
    }
}