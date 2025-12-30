package test.Day01BTest;

import org.junit.Test;
import software.aoc.day01.b.Dial;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class DialTest {
    private static final String orders = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """;

    @Test
    public void given_orders_should_account_the_times_that_position_is_zero() {
        assertThat(Dial.create().add("L1").count()).isEqualTo(0);
        assertThat(Dial.create().add("L1", "R1", "R50").count()).isEqualTo(1);
        assertThat(Dial.create().add("L51", "L500").count()).isEqualTo(6);
        assertThat(Dial.create().execute(orders).count()).isEqualTo(6);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(Dial.create().execute(ReadTestFile.using("day01/input.txt")).count()).isEqualTo(6684);
    }
}
