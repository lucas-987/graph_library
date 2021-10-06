package m1graf2021;

public class Edge implements Comparable<Edge> {
    private Node from;
    private Node to;
    private int idTo;
    private int weight;
    private int idFrom;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Edge(int idTo, int idFrom) {
        this.idTo = idTo;
        this.idFrom = idFrom;
    }

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int idTo, int weight, int idFrom) {
        this.idTo = idTo;
        this.weight = weight;
        this.idFrom = idFrom;
    }

    public Node from() {
        return from;
    }

    public Node to() {
        return to;
    }

    public Edge getSymmetric() {

    }

    boolean isSelfLoop() {

    }

    @Override
    public int compareTo(Edge edge) {
        return 0;
    }
}
