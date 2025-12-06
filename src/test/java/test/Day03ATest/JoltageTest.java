package test.Day03ATest;

import org.junit.Test;
import software.aoc.day01.b.Dial;
import software.aoc.day03.a.Joltage;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class JoltageTest {
    @Test
    public void given_unique_test_check_result() {
        assertThat(Joltage.execute("987654321")).isEqualTo(98);
    }

    @Test
    public void given_aoc_test_separated_check_results() {
        assertThat(Joltage.execute("987654321111111")).isEqualTo(98);
        assertThat(Joltage.execute("811111111111119")).isEqualTo(89);
        assertThat(Joltage.execute("234234234234278")).isEqualTo(78);
        assertThat(Joltage.execute("818181911112111")).isEqualTo(92);
    }

    @Test
    public void given_aoc_test_check_results() {
        String example = "987654321111111\n811111111111119\n234234234234278\n818181911112111";
        assertThat(Joltage.execute(example.split("\n"))).isEqualTo(357);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(Joltage.execute(ReadTestFile.using("day03-a/input.txt"))).isEqualTo(16973);
    }
}
