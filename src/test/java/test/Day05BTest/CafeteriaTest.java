package test.Day05BTest;

import org.junit.Test;
import software.aoc.day05.b.Cafeteria;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class CafeteriaTest {
    @Test
    public void given_unique_test_check_result() {
        Cafeteria cafeteria = new Cafeteria();
        assertThat(cafeteria.execute("3-5\n4-3\n\n12")).isEqualTo(3);
    }

    @Test
    public void given_multiple_test_check_result() {
        Cafeteria cafeteria = new Cafeteria();
        assertThat(cafeteria.execute("3-5\n10-14\n16-20\n12-18")).isEqualTo(14);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        Cafeteria cafeteria = new Cafeteria();
        assertThat(cafeteria.execute(ReadTestFile.string(ReadTestFile.using("day05/input.txt"), "\n")))
                .isEqualTo(365804144481581L);
    }
}
