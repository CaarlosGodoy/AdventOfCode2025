package test.Day11ATest;

import org.junit.Test;
import software.aoc.day08.a.Playground;
import software.aoc.day11.a.Reactor;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorTest {
    @Test
    public void given_multiple_test_check_result() {
        Reactor reactor = new Reactor();
        assertThat(reactor.execute("""
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
                """.split("\n"))).isEqualTo(5);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        Reactor reactor = new Reactor();
        assertThat(reactor.execute(ReadTestFile.using("day11/input.txt")))
                .isEqualTo(764);
    }
}
