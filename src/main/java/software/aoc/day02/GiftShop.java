package software.aoc.day02;

import java.util.Arrays;

public class GiftShop {
    private final InvalidPattern pattern;
    public GiftShop(InvalidPattern pattern) {
        this.pattern = pattern;
    }

    public long execute(String[] ranges) {
        return solve(ranges);
    }

    private long solve(String[] input) {
        return Arrays.stream(input)
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(Range::toRange)
                .flatMapToLong(Range::stream)
                .filter(pattern::isInvalid)
                .sum();
    }
}