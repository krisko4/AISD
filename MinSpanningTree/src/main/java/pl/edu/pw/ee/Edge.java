package pl.edu.pw.ee;

public class Edge implements Comparable<Edge> {

    private final String from;
    private final String to;
    public boolean isMST = false;
    private final int weight;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }



    public Edge(String from, String to, int weight) {
        if (from == null || to == null)
            throw new IllegalArgumentException("Constructor params in class Edge cannot be null.");
        if (from.equals(to)) {
            throw new IllegalArgumentException("Unable to create edge with loop. Edge vertices must be distinct values." +
                    " Please correct your input graph file.");
        }

        this.from = from;
        this.to = to;
        this.weight = weight;
    }


    @Override
    public int compareTo(Edge e) {
        if (e == null) {
            throw new IllegalArgumentException("Compared edge cannot be null");
        }
        return Integer.compare(weight, e.weight);
    }
}
