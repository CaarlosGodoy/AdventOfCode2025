package software.aoc.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dial {
    private final List<Order> orders = new ArrayList<>();
    private Dial() {}

    public static Dial create() {
        return new Dial();
    }

    public Dial add(String... orders) {
        Arrays.stream(orders)
                .filter(s -> s != null && !s.isBlank())
                .flatMap(s -> Arrays.stream(s.split("\n")))
                .map(this::parse)
                .forEach(this.orders::add);
        return this;
    }

    private Order parse(String order) {
        return new Order((order.trim().startsWith("L") ? -1 : 1) * Integer.parseInt(order.trim().substring(1)));
    }

    public int position() {
        return normalize(sum(orders.stream()));
    }

    private static int sum(Stream<Order> orders) {
        return orders.mapToInt(Order::step).sum() + 50;
    }

    private int normalize(int value) {
        return (value % 100 + 100) % 100;
    }
    public int countA() {
        return (int) IntStream.rangeClosed(1, orders.size())
                .map(this::sumPartial)
                .filter(s -> s == 0)
                .count();
    }

    private int sumPartial(int size) {
        return normalize(sum(orders.stream().limit(size)));
    }

    public int countB() {
        return orders.stream()
                .reduce(new Pointer(50, 0),
                        (p, o) -> new Pointer(normalize(p.value() + o.step()), getTotal(p, o)),
                        (p1, _) -> p1)
                .total();
    }
    private int getTotal(Pointer p, Order o) {
        return p.total() + (int) IntStream.rangeClosed(
                        Math.min(p.value(), p.value() + o.step()),
                        Math.max(p.value(), p.value() + o.step()))
                .filter(i -> i != p.value() && i % 100 == 0)
                .count();
    }
}