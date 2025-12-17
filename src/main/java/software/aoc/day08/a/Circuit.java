package software.aoc.day08.a;

import java.util.List;
import java.util.ArrayList;

public record Circuit(Coord c1, Coord c2, double distance) {
    public static class Connection {
        private List<Coord> connections;
        public Connection(List<Coord> connections) {
            this.connections = new ArrayList<>();
        }

        public void addConnection(Coord coord) {this.connections.add(coord);}

        public List<Coord> getConnections() {return this.connections;}
    }
}
