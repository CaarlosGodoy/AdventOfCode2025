package software.aoc.day06;

import java.util.stream.LongStream;

public class MultCommand implements Command {
    @Override
    public long execute(LongStream stream) {
        return stream.reduce(1, (a, b) -> a*b);
    }
}
