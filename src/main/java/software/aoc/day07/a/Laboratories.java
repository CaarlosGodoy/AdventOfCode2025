package software.aoc.day07.a;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Laboratories {
    public static int execute(String[] s) {
        int[] ballArray = new int[s[0].length()];
        ballArray[s[0].indexOf("S")] = 1;
        return Math.toIntExact(IntStream.rangeClosed(0, s.length - 2)
                .mapToLong(i -> {
                    return IntStream.rangeClosed(0, s[0].length() - 1)
                            .filter(j -> ballArray[j] == 1)
                            .filter(j -> {
                                if (s[i + 1].charAt(j) != '^') return false;
                                modifyArray(j, ballArray);
                                return true;
                            })
                            .count();
                })
                .sum());
    }

    private static void modifyArray(int j, int[] ballArray) {
        ballArray[j] = 0;
        ballArray[j + 1] = 1;
        ballArray[j - 1] = 1;
    }
}
