package test.Day02ATest;

import org.junit.Test;
import software.aoc.day02.a.GiftShop;
import test.ReadTestFile;

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
        """.split("\n"))).isEqualTo(99);

        assertThat(GiftShop.check("""
        998-1012
        """.split("\n"))).isEqualTo(1010);

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
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(GiftShop.check(ReadTestFile.using("day02/input.txt")))
                .isEqualTo(54234399924L);
    }
}