package software.aoc.day01.b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dial {
    private final List<Order> orders;
    private Dial() {
        this.orders = new ArrayList<>();
    }
    public static Dial create() {
        return new Dial();
    }

    public Dial add(String... orders) {
        Arrays.stream(orders)
                .map(this::parse)
                .forEach(this::add);
        return this;
    }

    private void add(Order order) {
        orders.add(order);
    }

    private Order parse(String order) {
        return new Order(signOf(order) * valueOf(order));
    }

    private int signOf(String order) {
        return order.charAt(0) == 'L' ? -1 : 1;
    }

    private int valueOf(String order) {
        return Integer.parseInt(order.substring(1));
    }

    public int position() {
        return normalize(sumAll());
    }

    private int sumAll() {
        return sum(orders.stream());
    }

    private int sumPartial(int size) {
        return sum(orders.stream().limit(size));
    }

    private static int floor_div_100(int x) {
        // Implementación segura de la función suelo (floor) para enteros y negativos
        int result = x / 100;
        if (x < 0 && x % 100 != 0) {
            result--;
        }
        return result;
    }

    private int checkZeros(int prevSum, int currentSum) {
        int A = Math.min(prevSum, currentSum);
        int B = Math.max(prevSum, currentSum);

        // Usar la fórmula de intervalo cerrado [A, B]: floor(B/M) - floor((A-1)/M)
        int count_B = floor_div_100(B);
        int count_A_minus_1 = floor_div_100(A - 1);

        return count_B - count_A_minus_1;
    }

    public int count() {
        return iterate()
                .map(v -> {
                    return checkZeros(sumPartial(v - 1), sumPartial(v));
                })
                .sum();
    }

    private IntStream iterate() {
        return IntStream.rangeClosed(1, orders.size());
    }

    private static int sum(Stream<Order> orders) {
        return orders.mapToInt(o -> o.step).sum() + 50;
    }

    private int normalize(int value) {
        return (value % 100 + 100) % 100;
    }

    public Dial execute(String orders) {
        return add(orders.split("\n"));
    }

    public Dial execute(String[] orders) {
        return add(orders);
    }

    public record Order(int step) { }
}