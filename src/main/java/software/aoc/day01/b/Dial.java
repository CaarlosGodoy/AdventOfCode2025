package software.aoc.day01.b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Dial {
    private final List<Order> orders = new ArrayList<>();

    private Dial() {}

    public static Dial create() {
        return new Dial();
    }

    public Dial execute(String orders) {
        return add(orders.split("\n"));
    }

    public Dial execute(String[] orders) {
        return add(orders);
    }

    private void add(Order order) {
        orders.add(order);
    }

    public Dial add(String... orders) {
        Arrays.stream(orders).filter(s -> s != null && !s.isBlank()).map(this::parse).forEach(this::add);
        return this;
    }

    private Order parse(String order) {
        return new Order((order.trim().startsWith("L") ? -1 : 1) * Integer.parseInt(order.trim().substring(1)));
    }

    public int count() {
        return orders.stream()
                .reduce(new Pointer(50, 0), (p, o) -> new Pointer(normalize(p.value() + o.step()), getTotal(p, o)), (p1, p2) -> p1)
                .total();
    }

    private int getTotal(Pointer p, Order o) {
        return p.total() + (int) IntStream.rangeClosed(Math.min(p.value(), p.value() + o.step()), Math.max(p.value(), p.value() + o.step()))
                .filter(i -> i != p.value() && i % 100 == 0).count();
    }

    private int normalize(int value) {
        return (value % 100 + 100) % 100;
    }

    public record Order(int step) {}
    private record Pointer(int value, int total) {}
}