package software.aoc.day10.a;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Factory {

    public static long execute(String[] s) {
        Stream.of(s).forEach(l -> {
            int[] lights = lightArray(l.split(" ")[0]);
            int[] actual = new int[lights.length];
            System.out.println(Arrays.toString(lights));
            System.out.println(Arrays.toString(actual));
            IntStream.rangeClosed(1, l.split(" ").length-2).forEach(i -> {
                System.out.println(l.split(" ")[i]);
            });
        });
        return 1L;
    }

    private static int[] lightArray(String s) {
        return IntStream.rangeClosed(0, s.length()-3)
                .map(i -> (s.charAt(i+1) == '#') ? 1: 0)
                .toArray();
    }
}
