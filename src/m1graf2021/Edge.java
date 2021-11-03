package m1graf2021;

public class Edge {
    private Node from;
    private Node to;
    private int weight;

    int idFrom ;
    int idTo;

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
        this.idTo = to.getId();
        this.weight = weight;
        this.idFrom =from.getId() ;
    }

    public Node from() {
        return from;
    }

    public Node to() {
        return to;
    }

    public Edge getSymmetric() {
        return Edge.this;
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
