package software.aoc.day09;

import java.util.*;
import java.util.stream.*;

public class MovieTheater {

    public static long executeA(String[] s) {
        List<Cord> cords = toCords(s);
        return solve(cords, false);
    }

    public static long executeB(String[] s) {
        List<Cord> cords = toCords(s);
        return solve(cords, true);
    }

    private static long solve(List<Cord> cords, boolean checkValidity) {
        return IntStream.range(0, cords.size() - 1)
                .parallel()
                .mapToLong(i -> IntStream.range(i + 1, cords.size())
                        .filter(j -> !checkValidity || isValid(cords, cords.get(i), cords.get(j)))
                        .mapToLong(j -> area(cords.get(i), cords.get(j)))
                        .max().orElse(0))
                .max().orElse(0);
    }

    private static boolean isValid(List<Cord> cords, Cord c1, Cord c2) {
        if (!isInside(cords, (c1.x() + c2.x()) / 2.0, (c1.y() + c2.y()) / 2.0)) return false;
        return IntStream.range(0, cords.size())
                .noneMatch(i -> crosses(
                        cords.get(i),
                        cords.get((i + 1) % cords.size()),
                        Math.min(c1.x(), c2.x()),
                        Math.max(c1.x(), c2.x()),
                        Math.min(c1.y(), c2.y()),
                        Math.max(c1.y(), c2.y()))
                );
    }

    private static boolean isInside(List<Cord> cords, double px, double py) {
        return IntStream.range(0, cords.size())
                .filter(i -> rayCrossesSegment(px, py, cords.get(i), cords.get((i + 1) % cords.size())))
                .count() % 2 != 0;
    }

    private static boolean rayCrossesSegment(double px, double py, Cord p1, Cord p2) {
        return isPointInWall(py, p1.y(), p2.y()) && px < pointOfImpact(py, p1, p2);
    }
    private static boolean isPointInWall(double py, int y1, int y2) { return (y1 > py) != (y2 > py); }
    private static double pointOfImpact(double py, Cord p1, Cord p2) {
        return (double) (p2.x() - p1.x()) * (py - p1.y()) / (p2.y() - p1.y()) + p1.x();
    }

    private static boolean crosses(Cord c1, Cord c2, int x1, int x2, int y1, int y2) {
        return (isVerticalWall(c1.x(), c2.x())) ?
                isBetween(c1.x(), x1, x2) && intersectsRange(c1.y(), c2.y(), y1, y2) :
                isBetween(c1.y(), y1, y2) && intersectsRange(c1.x(), c2.x(), x1, x2);
    }

    private static boolean isVerticalWall(int x1, int x2) {
        return x1 == x2;
    }

    private static boolean isBetween(int value, int min, int max) {
        return value > min && value < max;
    }

    private static boolean intersectsRange(int wallStart, int wallEnd, int rectStart, int rectEnd) {
        return Math.max(wallStart, wallEnd) > rectStart && Math.min(wallStart, wallEnd) < rectEnd;
    }

    private static long area(Cord c1, Cord c2) {
        return (long) (Math.abs(c2.x() - c1.x()) + 1) * (Math.abs(c2.y() - c1.y()) + 1);
    }

    private static List<Cord> toCords(String[] input) {
        return Stream.of(input).map(s -> new Cord(
                Integer.parseInt(s.split(",")[0].trim()),
                Integer.parseInt(s.split(",")[1].trim()))
        ).toList();
    }
}