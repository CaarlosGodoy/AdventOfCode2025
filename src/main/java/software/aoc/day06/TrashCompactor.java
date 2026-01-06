package software.aoc.day06;

public class TrashCompactor {
    public static long execute(int part, String[] s) {
        return (part == 1) ? new CalculatorA().calculate(s) : new CalculatorB().calculate(s);
    }
}