package test.Day09BTest;

import org.junit.Test;
import software.aoc.day09.Cord;
import software.aoc.day09.b.MovieTheater;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTheaterTest {
    @Test
    public void area_test() {
        assertThat(MovieTheater.area(new Cord(new String[]{"2", "5"}), new Cord(new String[]{"9", "7"})))
                .isEqualTo(24);
        assertThat(MovieTheater.area(new Cord(new String[]{"7", "1"}), new Cord(new String[]{"11", "7"})))
                .isEqualTo(35);
        assertThat(MovieTheater.area(new Cord(new String[]{"7", "3"}), new Cord(new String[]{"2", "3"})))
                .isEqualTo(6);
    }

    @Test
    public void given_multiple_test_check_result() {
        assertThat(MovieTheater.execute("""
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
        assertThat(MovieTheater.execute(ReadTestFile.using("day09/input.txt")))
                .isEqualTo(1470616992L);
    }
}
