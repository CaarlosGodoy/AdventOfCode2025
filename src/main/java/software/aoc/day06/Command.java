package software.aoc.day06;

import java.util.stream.LongStream;

public interface Command {
    long execute(LongStream stream);
}
