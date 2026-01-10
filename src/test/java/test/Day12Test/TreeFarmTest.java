package test.Day12Test;

import org.junit.Test;
import software.aoc.day12.TreeFarm;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeFarmTest {
    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(TreeFarm.execute(ReadTestFile.using("day12/input.txt")))
                .isEqualTo(427);
    }
}
