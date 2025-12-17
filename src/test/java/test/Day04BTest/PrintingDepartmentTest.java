package test.Day04BTest;

import org.junit.Test;
import software.aoc.day04.b.PrintingDepartment;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintingDepartmentTest {
    @Test
    public void given_simple_test_check_result() {
        assertThat(software.aoc.day04.a.PrintingDepartment.execute("""
                @@@
                @@@
                @@@
                """.split("\n"))).isEqualTo(4L);
    }

    @Test
    public void given_aoc_test_check_result() {
        PrintingDepartment department = new PrintingDepartment();
        assertThat(department.execute("""
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
                """.split("\n"))).isEqualTo(43L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        PrintingDepartment department = new PrintingDepartment();
        assertThat(department.execute(ReadTestFile.using("day04/input.txt"))).isEqualTo(8887L);
    }
}
