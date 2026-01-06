package software.aoc.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Cafeteria {

    public static int executeA(String s) {
        return (int) ids(s)
                .filter(id -> ranges(s).stream().anyMatch(r -> r.contains(id)))
                .count();
    }

    private static List<Range> ranges(String s) {
        return Arrays.stream(s.split("\n\n")[0].split("\n"))
                .map(Range::of)
                .toList();
    }

    private static java.util.stream.Stream<Long> ids(String s) {
        return Arrays.stream(s.split("\n\n")[1].split("\n"))
                .map(Long::parseLong);
    }



    public static long executeB(String s) {
        return sum(merge(sortedRanges(s)));
    }

    private static List<Range> sortedRanges(String s) {
        return Arrays.stream(s.split("\n\n")[0].split("\n"))
                .map(Range::of)
                .sorted(Comparator.comparingLong(Range::from))
                .toList();
    }

    private static List<Range> merge(List<Range> sorted) {
        return sorted.stream()
                .collect(ArrayList::new, Cafeteria::processRange, ArrayList::addAll);
    }
    private static void processRange(ArrayList<Range> ranges, Range range) {
        if (ranges.isEmpty() || range.from() > ranges.getLast().to()) ranges.add(range);
        else {
            Range last = ranges.removeLast();
            ranges.add(new Range(last.from(), Math.max(last.to(), range.to())));
        }
    }

    private static long sum(List<Range> merged) {
        return merged.stream()
                .mapToLong(Range::size)
                .sum();
    }
}