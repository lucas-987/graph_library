package m1graf2021;

public class Edge {
    private Node from;
    private Node to;
    private int weight;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Edge(int idFrom, int idTo) {
        this.from = new Node(idFrom);
        this.to = new Node(idTo);
    }

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int idFrom, int idTo, int weight) {
        this.from = new Node(idFrom);
        this.weight = weight;
        this.to = new Node(idTo);
    }

    public Node from() {
        return from;
    }

    public Node to() {
        return to;
    }

    public Edge getSymmetric() {
        return new Edge(this.to, this.from);
    }

    boolean isSelfLoop() {
        return this.from.equals(this.to);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) {
            return false;
        }
        return this.from.equals(((Edge) obj).from) && this.to.equals(((Edge) obj).to);
    }
}
