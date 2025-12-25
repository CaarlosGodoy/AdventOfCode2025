package test.Day02BTest;

import org.junit.Test;
import software.aoc.day02.b.GiftShop;
import software.aoc.day05.a.Cafeteria;
import software.aoc.day10.a.Factory;
import test.ReadTestFile;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftShopTest {
    @Test
    public void given_one_range_check_invalid_ids() {
        assertThat(GiftShop.check("""
                11-22
                """.split("\n"))).isEqualTo(33);
    }

    @Test
    public void given_multiple_ranges_check_invalid_ids() {
        assertThat(GiftShop.check("""
                11-22
                3-9
                1188511880-1188511890
                """.split("\n"))).isEqualTo(1188511918);
    }

    @Test
    public void given_zero_invalid_ids_check_the_result_equals_zero() {
        assertThat(GiftShop.check("""
                12-21
                3-9
                """.split("\n"))).isEqualTo(0);
    }

    @Test
    public void given_tests_by_aoc_check_results() {assertThat(GiftShop.check("""
        11-22
        """.split("\n"))).isEqualTo(33);

        assertThat(GiftShop.check("""
        95-115
        """.split("\n"))).isEqualTo(210);

        assertThat(GiftShop.check("""
        998-1012
        """.split("\n"))).isEqualTo(2009);

        assertThat(GiftShop.check("""
        1188511880-1188511890
        """.split("\n"))).isEqualTo(1188511885);

        assertThat(GiftShop.check("""
        222220-222224
        """.split("\n"))).isEqualTo(222222);

        assertThat(GiftShop.check("""
        1698522-1698528
        """.split("\n"))).isEqualTo(0);

        assertThat(GiftShop.check("""
        446443-446449
        """.split("\n"))).isEqualTo(446446);

        assertThat(GiftShop.check("""
        38593856-38593862
        """.split("\n"))).isEqualTo(38593859);

        assertThat(GiftShop.check("""
        565653-565659
        """.split("\n"))).isEqualTo(565656);

        assertThat(GiftShop.check("""
        824824821-824824827
        """.split("\n"))).isEqualTo(824824824);

        assertThat(GiftShop.check("""
        2121212118-2121212124
        """.split("\n"))).isEqualTo(2121212121);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(GiftShop.check(ReadTestFile.using("day02/input.txt")))
                .isEqualTo(4781377701L);
    }
}