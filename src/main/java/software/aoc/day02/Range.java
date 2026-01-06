package software.aoc.day02;

import java.util.stream.LongStream;

public record Range(long from, long to) {
    public static Range toRange(String range) {
        return new Range(Long.parseLong(range.split("-")[0]), Long.parseLong(range.split("-")[1]));
    }

    public LongStream stream() {
        return LongStream.rangeClosed(from, to);
    }
}
