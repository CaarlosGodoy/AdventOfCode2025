package test.Day08BTest;

import org.junit.Test;
import software.aoc.day08.Playground;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaygroundTest {
    @Test
    public void given_multiple_test_check_result() {
        assertThat(Playground.create().executeB("""
                162,817,812
                57,618,57
                906,360,560
                592,479,940
                352,342,300
                466,668,158
                542,29,236
                431,825,988
                739,650,466
                52,470,668
                216,146,977
                819,987,18
                117,168,530
                805,96,715
                346,949,466
                970,615,88
                941,993,340
                862,61,35
                984,92,344
                425,690,689
                """.split("\n"))).isEqualTo(25272L);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(Playground.create().executeB(ReadTestFile.using("day08/input.txt")))
                .isEqualTo(8079278220L);
    }
}
