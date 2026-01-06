package software.aoc.day06;

import java.util.stream.LongStream;

public class SumCommand implements Command {
    @Override
    public long execute(LongStream stream) {
        return stream.sum();
    }
}
