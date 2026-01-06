package software.aoc.day04;

import java.util.stream.IntStream;

public class Grid {
    private final String[] grid;

    public Grid(String[] grid) {
        this.grid = grid;
    }

    public int checkSurroundings(int i, int j) {
        return IntStream.range(i - 1, i + 2)
                .flatMap(k -> IntStream.range(j - 1, j + 2)
                        .map(l -> ((posExists(k, l) && notCenter(i, j, k, l)) && grid[k].charAt(l) == '@') ? 1 : 0)
                ).sum();
    }

    private boolean posExists(int k, int l) {
        return (k >= 0 && k < grid.length && l >= 0 && l < grid[k].length());
    }

    private boolean notCenter(int i, int j, int k, int l) {
        return k != i || l != j;
    }

    public char at(int i, int j) { return grid[i].charAt(j); }
    public int rows() { return grid.length; }
    public int cols(int i) { return grid[i].length(); }
    public void setDot(int i, int j) {grid[i] = grid[i].substring(0, j) + "." + grid[i].substring(j + 1); }
}