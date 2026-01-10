package test.Day09BTest;

import org.junit.Test;
import software.aoc.day09.MovieTheater;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTheaterTest {
    @Test
    public void given_multiple_test_check_result() {
        assertThat(MovieTheater.executeB("""
                7,1
                11,1
                11,7
                9,7
                9,5
                2,5
                2,3
                7,3
                """.split("\n"))).isEqualTo(24L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(MovieTheater.executeB(ReadTestFile.using("day09/input.txt")))
                .isEqualTo(1470616992L);
    }
}
