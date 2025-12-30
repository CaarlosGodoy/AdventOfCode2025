package test.Day12Test;

import org.junit.Test;
import software.aoc.day12.TreeFarm;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeFarmTest {
    @Test
    public void given_aoc_test_check_result() {
        assertThat(TreeFarm.execute("""
                0:
                ###
                ##.
                ##.
                
                1:
                ###
                ##.
                .##
                
                2:
                .##
                ###
                ##.
                
                3:
                ##.
                ###
                ##.
                
                4:
                ###
                #..
                ###
                
                5:
                ###
                .#.
                ###
                
                4x4: 0 0 0 0 2 0
                12x5: 1 0 1 0 2 2
                12x5: 1 0 1 0 3 2
                """.split("\n"))).isEqualTo(2);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(TreeFarm.execute(ReadTestFile.using("day12/input.txt")))
                .isEqualTo(427);
    }
}
