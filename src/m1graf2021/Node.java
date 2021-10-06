package m1graf2021;

public class Node implements Comparable<Node> {
    private int id;
    private String name;

    public Node(int id){
   this.id = id;
    }

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Node() {
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Node node) {
        return 0;
    }
}
