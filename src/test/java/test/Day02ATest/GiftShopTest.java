package test.Day02ATest;

import org.junit.Test;
import software.aoc.day02.GiftShop;
import software.aoc.day02.a.InvalidPatternOne;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftShopTest {
    @Test
    public void given_one_range_check_invalid_ids() {
        GiftShop giftShop = new GiftShop(new InvalidPatternOne());
        assertThat(giftShop.execute("""
                11-22
                """.split("\n"))).isEqualTo(33);
    }

    @Test
    public void given_multiple_ranges_check_invalid_ids() {
        GiftShop giftShop = new GiftShop(new InvalidPatternOne());
        assertThat(giftShop.execute("""
                11-22
                3-9
                1188511880-1188511890
                """.split("\n"))).isEqualTo(1188511918);
    }

    @Test
    public void given_zero_invalid_ids_check_the_result_equals_zero() {
        GiftShop giftShop = new GiftShop(new InvalidPatternOne());
        assertThat(giftShop.execute("""
                12-21
                3-9
                """.split("\n"))).isEqualTo(0);
    }

    @Test
    public void given_tests_by_aoc_check_results() {
        GiftShop giftShop = new GiftShop(new InvalidPatternOne());
        assertThat(giftShop.execute("""
        11-22
        """.split("\n"))).isEqualTo(33);

        assertThat(giftShop.execute("""
        95-115
        """.split("\n"))).isEqualTo(99);

        assertThat(giftShop.execute("""
        998-1012
        """.split("\n"))).isEqualTo(1010);

        assertThat(giftShop.execute("""
        1188511880-1188511890
        """.split("\n"))).isEqualTo(1188511885);

        assertThat(giftShop.execute("""
        222220-222224
        """.split("\n"))).isEqualTo(222222);

        assertThat(giftShop.execute("""
        1698522-1698528
        """.split("\n"))).isEqualTo(0);

        assertThat(giftShop.execute("""
        446443-446449
        """.split("\n"))).isEqualTo(446446);

        assertThat(giftShop.execute("""
        38593856-38593862
        """.split("\n"))).isEqualTo(38593859);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        GiftShop giftShop = new GiftShop(new InvalidPatternOne());
        assertThat(giftShop.execute(ReadTestFile.using("day02/input.txt")))
                .isEqualTo(54234399924L);
    }
}