package test.Day06BTest;

import org.junit.Test;
import software.aoc.day06.TrashCompactor;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class TrashCompactorTest {
    @Test
    public void given_simple_test_check_result() {
        assertThat(TrashCompactor.execute(2, """
                64
                23
                314
                +
                """.split("\n"))).isEqualTo(1058L);
    }

    @Test
    public void given_multiple_test_check_result() {
        assertThat(TrashCompactor.execute(2, """
                123 328  51 64
                 45 64  387 23
                  6 98  215 314
                *   +   *   +
                """.split("\n"))).isEqualTo(3263827L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(TrashCompactor.execute(2, ReadTestFile.using("day06/input.txt"))).isEqualTo(11708563470209L);
    }
}
