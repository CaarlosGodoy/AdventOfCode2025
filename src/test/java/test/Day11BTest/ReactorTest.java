package test.Day11BTest;

import org.junit.Test;
import software.aoc.day11.Reactor;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorTest {
    @Test
    public void given_multiple_test_check_result() {
        Reactor reactor = new Reactor();
        assertThat(reactor.executeB("""
                svr: aaa bbb
                aaa: fft
                fft: ccc
                bbb: tty
                tty: ccc
                ccc: ddd eee
                ddd: hub
                hub: fff
                eee: dac
                dac: fff
                fff: ggg hhh
                ggg: out
                hhh: out
                """.split("\n"), "svr")).isEqualTo(2L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        Reactor reactor = new Reactor();
        assertThat(reactor.executeB(ReadTestFile.using("day11/input.txt"), "svr"))
                .isEqualTo(462444153119850L);
    }
}
