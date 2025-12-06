package test.Day05ATest;

import org.junit.Test;
import software.aoc.day03.b.Joltage;
import software.aoc.day05.a.Cafeteria;
import test.ReadTestFile;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CafeteriaTest {
    @Test
    public void given_unique_test_check_result() {
        assertThat(Cafeteria.execute("3-5\n\n1\n4\n9")).isEqualTo(1);
    }

    @Test
    public void given_multiple_test_check_result() {
        assertThat(Cafeteria.execute("3-5\n10-14\n16-20\n12-18\n\n1\n5\n8\n11\n17\n32")).isEqualTo(3);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(Cafeteria.execute(ReadTestFile.string(ReadTestFile.using("day05-a/input.txt"), "\n")))
                .isEqualTo(640);
    }
}
