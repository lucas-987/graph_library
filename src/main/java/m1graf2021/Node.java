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

    public int getId() {
        return id;
    }

    
    @Override
    public boolean equals(Object obj) {
        if( !(obj instanceof Node) ) {
     		return false;
        }
     	return this.getId() == ((Node)obj).getId();
     }
    
    @Override
    public int compareTo(Node node) {
        if (Node.this.id< node.id){
            return -1;
        }else if (Node.this.id>node.id){
            return 1;
        }else{
            return 0;
        }
    }
}
