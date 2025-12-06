package software.aoc.day02.a;

import java.util.Arrays;
import java.util.stream.LongStream;

public class GiftShop {
    public static long check(String... ranges) {
        return Arrays.stream(ranges)
                .mapToLong(range -> checkInvalidIds(getIds(range)))
                .sum();
    }

    private static long checkInvalidIds(LongStream ids) {
        return ids.filter(id -> checkInvaidId(String.valueOf(id))).sum();
    }

    private static boolean checkInvaidId(String id) {
        return id.substring(0, id.length() / 2).equals(id.substring(id.length() / 2));
    }

    private static LongStream getIds(String id) {
        return LongStream.rangeClosed(getFrom(id), getTo(id));
    }

    private static long getFrom(String id) {
        return Long.parseLong(id.split("-")[0]);
    }

    private static long getTo(String id) {
        return Long.parseLong(id.split("-")[1]);
    }
}
