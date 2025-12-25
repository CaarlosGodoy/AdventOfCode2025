package software.aoc.day09.a;

import software.aoc.day09.Cord;
import java.util.stream.IntStream;

public class MovieTheater {
    public static long execute(String[] s) {
        return IntStream.rangeClosed(0, s.length - 2)
                .mapToLong(i -> {
                    return IntStream.rangeClosed(i+1, s.length - 1)
                            .filter(j -> i != j)
                            .mapToLong(j -> area(toCoord(s[i]), toCoord(s[j])))
                            .max().orElse(0);
                })
                .max().orElse(0);
    }

    private static Cord toCoord(String s) {
        return new Cord(s.split(","));
    }

    public static long area(Cord c1, Cord c2) {       ;
        return (long) (Math.abs(c2.x() - c1.x()) + 1) * (Math.abs(c2.y() - c1.y()) + 1);
    }
}
