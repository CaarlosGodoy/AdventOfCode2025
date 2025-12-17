package test.Day10ATest;

import org.junit.Test;
import software.aoc.day10.a.Factory;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class FactoryTest {
    @Test
    public void given_unique_test_check_result() {
        assertThat(Factory.execute("""
                [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
                """.split("\n"))).isEqualTo(2L);
    }
    @Test
    public void given_multiple_test_check_result() {
        assertThat(Factory.execute("""
                [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}
                [...#.] (0,2,3,4) (2,3) (0,4) (0,1,2) (1,2,3,4) {7,5,12,7,2}
                [.###.#] (0,1,2,3,4) (0,3,4) (0,1,2,4,5) (1,2) {10,11,11,5,10,5}
                """.split("\n"))).isEqualTo(7L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(Factory.execute(ReadTestFile.using("day10/input.txt")))
                .isEqualTo(4781377701L);
    }
}
