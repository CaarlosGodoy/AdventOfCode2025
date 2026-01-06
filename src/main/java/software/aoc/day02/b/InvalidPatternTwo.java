package software.aoc.day02.b;

import software.aoc.day02.InvalidPattern;

public class InvalidPatternTwo implements InvalidPattern {
    @Override
    public boolean isInvalid(long id) {
        return check(String.valueOf(id));
    }

    private boolean check(String id) {
        return id.length() >= 2 && id.matches("^(\\d+)\\1+$");
    }
}
