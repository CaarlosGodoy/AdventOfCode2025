package software.aoc.day05.b;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Cafeteria {
    public static long execute(String s) {
        return getIds(s.split("\n\n")[0].split("\n"));
    }

    private static int getIds(String[] split) {
        Arrays.stream(split)
                .flatMapToLong(l -> LongStream
                        .rangeClosed(Long.parseLong(l.split("-")[0]),
                                Long.parseLong(l.split("-")[1]))
                )
                .forEach(System.out::println);
        return 3;
        /*
        return Arrays.stream(split)
                .flatMapToLong(l -> LongStream
                        .rangeClosed(Long.parseLong(l.split("-")[0]),
                                     Long.parseLong(l.split("-")[1]))
                        )
                .boxed()
                .collect(Collectors.toSet()).size();

         */
    }
}
