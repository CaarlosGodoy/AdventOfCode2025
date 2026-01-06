package software.aoc.day04.a;

import software.aoc.day04.Grid;
import java.util.stream.IntStream;

public class PrintingDepartment {
    public static long execute(String[] s) {
        return checkPaper(new Grid(s));
    }

    private static long checkPaper(Grid grid) {
        return IntStream.range(0, grid.rows())
                .flatMap(i -> IntStream.range(0, grid.cols(i))
                        .map(j -> (grid.at(i, j) == '@' && grid.checkSurroundings(i, j) < 4) ? 1 : 0))
                .sum();
    }
}