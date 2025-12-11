package test.Day03BTest;

import org.junit.Test;
import software.aoc.day03.b.Joltage;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class JoltageTest {
    @Test
    public void given_unique_test_check_result() {
        assertThat(Joltage.execute("9876543211111111")).isEqualTo(987654321111L);
    }

    @Test
    public void given_aoc_test_separated_check_results() {
        assertThat(Joltage.execute("987654321111111")).isEqualTo(987654321111L);
        assertThat(Joltage.execute("811111111111119")).isEqualTo(811111111119L);
        assertThat(Joltage.execute("234234234234278")).isEqualTo(434234234278L);
        assertThat(Joltage.execute("818181911112111")).isEqualTo(888911112111L);
    }

    @Test
    public void given_aoc_test_check_results() {
        String example = "987654321111111\n811111111111119\n234234234234278\n818181911112111";
        assertThat(Joltage.execute(example.split("\n"))).isEqualTo(3121910778619L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(Joltage.execute(ReadTestFile.using("day03/input.txt"))).isEqualTo(16973);
    }
}
