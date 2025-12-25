package software.aoc.day09.b;

import software.aoc.day09.Cord;
import java.util.stream.IntStream;

public class MovieTheater {
    public static long execute(String[] s) {
        return IntStream.rangeClosed(0, s.length - 2)
                .parallel()
                .mapToLong(i -> {
                    return IntStream.rangeClosed(i + 1, s.length - 1)
                            .filter(j -> isValid(s, toCoord(s[i]), toCoord(s[j])))
                            .mapToLong(j -> area(toCoord(s[i]), toCoord(s[j])))
                            .max().orElse(0);
                })
                .max().orElse(0);
    }

    private static boolean isValid(String[] s, Cord c1, Cord c2) {
        if (!isInside(s, (c1.x() + c2.x()) / 2.0, (c1.y() + c2.y()) / 2.0)) return false;

        return IntStream.range(0, s.length)
                .noneMatch(i -> crosses(
                        toCoord(s[i]),
                        toCoord(s[(i + 1) % s.length]),
                        Math.min(c1.x(), c2.x()),
                        Math.max(c1.x(), c2.x()),
                        Math.min(c1.y(), c2.y()),
                        Math.max(c1.y(), c2.y()))
                );
    }


    private static boolean isInside(String[] s, double px, double py) {
        return IntStream.range(0, s.length)
                .filter(i -> rayCrossesSegment(px, py, toCoord(s[i]), toCoord(s[(i + 1) % s.length])))
                .count() % 2 != 0;
    }

    private static boolean rayCrossesSegment(double px, double py, Cord p1, Cord p2) {
        return isBetweenHeights(py, p1, p2) && isToTheLeftOfInterception(px, py, p1, p2);
    }
    private static boolean isBetweenHeights(double py, Cord p1, Cord p2) {
        return (p1.y() > py) != (p2.y() > py);
    }
    private static boolean isToTheLeftOfInterception(double px, double py, Cord p1, Cord p2) {
        return px < (double) (p2.x() - p1.x()) * (py - p1.y()) / (p2.y() - p1.y()) + p1.x();
    }


    private static boolean crosses(Cord c1, Cord c2, int x1, int x2, int y1, int y2) {
        return verticalWall(c1, c2) ?
                intersectsVertical(c1, c2, x1, x2, y1, y2) :
                intersectsHorizontal(c1, c2, x1, x2, y1, y2);
    }

    private static boolean verticalWall(Cord c1, Cord c2) {
        return c1.x() == c2.x();
    }

    private static boolean intersectsVertical(Cord p1, Cord p2, int x1, int x2, int y1, int y2) {
        return isXBetween(p1.x(), x1, x2) && overlapsY(p1, p2, y1, y2);
    }
    private static boolean isXBetween(int px, int x1, int x2) {
        return px > x1 && px < x2;
    }
    private static boolean overlapsY(Cord p1, Cord p2, int y1, int y2) {
        return Math.max(p1.y(), p2.y()) > y1 && Math.min(p1.y(), p2.y()) < y2;
    }

    private static boolean intersectsHorizontal(Cord p1, Cord p2, int x1, int x2, int y1, int y2) {
        return isYBetween(p1.y(), y1, y2) && overlapsX(p1, p2, x1, x2);
    }
    private static boolean isYBetween(int py, int y1, int y2) {
        return py > y1 && py < y2;
    }
    private static boolean overlapsX(Cord p1, Cord p2, int x1, int x2) {
        return Math.max(p1.x(), p2.x()) > x1 && Math.min(p1.x(), p2.x()) < x2;
    }



    public static long area(Cord c1, Cord c2) {
        return (long) (Math.abs(c2.x() - c1.x()) + 1) * (Math.abs(c2.y() - c1.y()) + 1);
    }

    private static Cord toCoord(String s) {
        return new Cord(s.split(","));
    }
}