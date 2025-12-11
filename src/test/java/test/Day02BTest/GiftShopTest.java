package test.Day02BTest;

import org.junit.Test;
import software.aoc.day02.b.GiftShop;
import test.ReadTestFile;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftShopTest {
    @Test
    public void given_one_range_check_invalid_ids() {
        assertThat(GiftShop.check("11-22")).isEqualTo(33);
    }

    @Test
    public void given_multiple_ranges_check_invalid_ids() {
        assertThat(GiftShop.check("11-22", "3-9", "1188511880-1188511890")).isEqualTo(1188511918);
    }

    @Test
    public void given_zero_invalid_ids_check_the_result_equals_zero() {
        assertThat(GiftShop.check("3-9", "12-21")).isEqualTo(0);
    }

    @Test
    public void given_tests_by_aoc_check_results() {
        assertThat(GiftShop.check("11-22")).isEqualTo(33);
        assertThat(GiftShop.check("95-115")).isEqualTo(210);
        assertThat(GiftShop.check("998-1012")).isEqualTo(2009);
        assertThat(GiftShop.check("1188511880-1188511890")).isEqualTo(1188511885);
        assertThat(GiftShop.check("222220-222224")).isEqualTo(222222);
        assertThat(GiftShop.check("1698522-1698528")).isEqualTo(0);
        assertThat(GiftShop.check("446443-446449")).isEqualTo(446446);
        assertThat(GiftShop.check("38593856-38593862")).isEqualTo(38593859);
        assertThat(GiftShop.check("565653-565659")).isEqualTo(565656);
        assertThat(GiftShop.check("824824821-824824827")).isEqualTo(824824824);
        assertThat(GiftShop.check("2121212118-2121212124")).isEqualTo(2121212121);
    }

    @Test
    public void given_aoc_puzzle_check_result() {
        assertThat(GiftShop.check(ReadTestFile.using("day02/input.txt"))).isEqualTo(70187097315L);
    }
}