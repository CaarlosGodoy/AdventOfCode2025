package software.aoc.day05.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.LongStream;

public class Cafeteria {
    private final List<String> differentRanges;

    public Cafeteria() {
        this.differentRanges = new ArrayList<>();
    }

    public long execute(String s) {
        differentRanges.clear();
        Arrays.stream(s.split("\n\n")[0].split("\n")).forEach(r -> addRange(sorted(r)));
        return differentRanges.stream()
                .mapToLong(Cafeteria::countValuesIn)
                .sum();
    }

    private void addRange(String range) {
        if (!contains(range)) this.differentRanges.add(range);
        else updateList(range, mergedRange(range));
    }
    private String sorted(String range) {
        if (Long.parseLong(range.split("-")[0]) > Long.parseLong(range.split("-")[1]))
            return range.split("-")[1] + "-" + range.split("-")[0];
        return range;
    }

    private void updateList(String range, String rangeFromList) {
        differentRanges.remove(rangeFromList);
        addRange(mergedRange(range, rangeFromList));
    }

    private String mergedRange(String range) {
        return differentRanges.stream()
                .filter(r -> {
                    long startR = Long.parseLong(r.split("-")[0]);
                    long endR = Long.parseLong(r.split("-")[1]);
                    long startRange = Long.parseLong(range.split("-")[0]);
                    long endRange = Long.parseLong(range.split("-")[1]);
                    return contains(startRange, endRange, startR, endR);
                })
                .findFirst().orElse(null);
    }
    private String mergedRange(String range, String rangeFromList) {
        return minFrom(range.split("-")[0], rangeFromList.split("-")[0]) + "-" +
                maxTo(range.split("-")[1], rangeFromList.split("-")[1]);
    }
    private String minFrom(String from1, String from2) {
        return String.valueOf(Math.min(Long.parseLong(from1), Long.parseLong(from2)));
    }

    private String maxTo(String to1, String to2) {
        return String.valueOf(Math.max(Long.parseLong(to1), Long.parseLong(to2)));
    }

    private boolean contains(String range) {
        return differentRanges.stream()
                .anyMatch(r -> contains(
                        Long.parseLong(range.split("-")[0]),
                        Long.parseLong(range.split("-")[1]),
                        Long.parseLong(r.split("-")[0]),
                        Long.parseLong(r.split("-")[1]))
                );
    }
    private boolean contains(long fromNew, long toNew, long fromList, long toList) {
        return !((toList < fromNew) || (toNew < fromList));
    }

    private static long countValuesIn(String r) {
        return LongStream.rangeClosed(
                Long.parseLong(r.split("-")[0]),
                Long.parseLong(r.split("-")[1])
        ).count();
    }
}