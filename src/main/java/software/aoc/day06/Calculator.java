package software.aoc.day06;

interface Calculator {
    long calculate(String[] s);

    default Command getCommand(String sign) {
        return sign.equals("+") ? new SumCommand() : new MultCommand();
    }
}
