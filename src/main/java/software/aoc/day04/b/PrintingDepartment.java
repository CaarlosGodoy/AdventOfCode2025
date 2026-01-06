package software.aoc.day04.b;

import software.aoc.day04.Grid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintingDepartment {

    public long execute(String[] s) {
        return Stream.generate(() -> iterate(new Grid(s)))
                .takeWhile(count -> count > 0)
                .mapToLong(Long::longValue)
                .sum();
    }

    private long iterate(Grid grid) {
        return updatePaper(grid, checkPaper(grid));
    }

    private List<int[]> checkPaper(Grid grid) {
        List<int[]> toChange = new ArrayList<>();
        IntStream.range(0, grid.rows())
                .forEach(i -> IntStream.range(0, grid.cols(i))
                        .forEach(j -> {
                            if (grid.at(i, j) == '@' && grid.checkSurroundings(i, j) < 4) {
                                toChange.add(new int[]{i, j});
                            }
                        })
                );
        return toChange;
    }

    private long updatePaper(Grid grid, List<int[]> toChange) {
        toChange.forEach(a -> grid.setDot(a[0], a[1]));
        return toChange.size();
    }
}