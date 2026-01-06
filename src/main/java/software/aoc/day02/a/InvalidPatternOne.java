package software.aoc.day02.a;

import software.aoc.day02.InvalidPattern;

public class InvalidPatternOne implements InvalidPattern {
    @Override
    public boolean isInvalid(long id) {
        return check(String.valueOf(id));
    }

    private boolean check(String id) {
        return id.substring(0, id.length() / 2).equals(id.substring(id.length() / 2));
    }
}
