package test.Day04ATest;

import org.junit.Test;
import software.aoc.day04.a.PrintingDepartment;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintingDepartmentTest {
    @Test
    public void given_simple_test_check_result() {
        assertThat(PrintingDepartment.execute("""
                @@@
                @@@
                @@@
                """.split("\n"))).isEqualTo(4L);
    }

    @Test
    public void given_aoc_test_check_result() {
        assertThat(PrintingDepartment.execute("""
                ..@@.@@@@.
                @@@.@.@.@@
                @@@@@.@.@@
                @.@@@@..@.
                @@.@@@@.@@
                .@@@@@@@.@
                .@.@.@.@@@
                @.@@@.@@@@
                .@@@@@@@@.
                @.@.@@@.@.
                """.split("\n"))).isEqualTo(13L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(PrintingDepartment.execute(ReadTestFile.using("day04/input.txt"))).isEqualTo(1549L);
    }
}
