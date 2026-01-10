package test.Day11ATest;

import org.junit.Test;
import software.aoc.day11.Reactor;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorTest {
    @Test
    public void given_multiple_test_check_result() {
        Reactor reactor = new Reactor();
        assertThat(reactor.executeA("""
                aaa: you hhh
                you: bbb ccc
                bbb: ddd eee
                ccc: ddd eee fff
                ddd: ggg
                eee: out
                fff: out
                ggg: out
                hhh: ccc fff iii
                iii: out
                """.split("\n"), "you")).isEqualTo(5);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        Reactor reactor = new Reactor();
        assertThat(reactor.executeA(ReadTestFile.using("day11/input.txt"), "you"))
                .isEqualTo(764);
    }
}
