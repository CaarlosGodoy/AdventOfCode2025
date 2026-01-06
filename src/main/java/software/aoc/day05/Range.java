package software.aoc.day05;

public record Range(long from, long to) {
    public static Range of(String s) {
        return new Range(getMin(s.split("-")), getMax(s.split("-")));
    }
    private static long getMin(String[] split) {
        return Math.min(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }
    private static long getMax(String[] split) {
        return Math.max(Long.parseLong(split[0]), Long.parseLong(split[1]));
    }

    public boolean contains(long id) {
        return id >= from && id <= to;
    }

    public long size() {
        return to - from + 1;
    }
}