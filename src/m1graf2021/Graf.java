package m1graf2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graf {
    Map<Node, List<Edge>> adjEdList;

    public Graf() {

    };

    public Graf(int... number) {
        this.adjEdList = new HashMap<>();

        List<Integer> test = new ArrayList<>();

        if(number.length > 0) {
            //noeud1
            int currentNodeId = 1;
            for(int i=0; i< number.length; i++) {
                int val = number[i];

                if(val == 0) {
                    Node node = new Node(currentNodeId);
                    List<Edge> edges = new ArrayList<>();
                    for(int destNodeId : test) {
                        edges.add(new Edge(currentNodeId, destNodeId));
                    }

                    adjEdList.put(node, edges);
                    test = new ArrayList<>();

                    if(i + 1 < number.length)
                        currentNodeId++;
                }
                else {
                    test.add(val);
                }
            }
        }
    }

    public int nbNodes() {
        return this.adjEdList.size();
    }

    public boolean existsNode(Node n) {
        return adjEdList.containsKey(n);
    }

    public boolean existsNode(int id) {
        for(Node key : adjEdList.keySet()) {
            if(key.getId() == id) return true;
        }
        return false;
    }

    public Node getNode(int id) {
        for(Node key : adjEdList.keySet()) {
            if(key.getId() == id) return key;
        }
        return null;
    }

    public void addNode(Node n) {
        adjEdList.put(n, new ArrayList<Edge>());
    }

    public void addNode(int id) {
        adjEdList.put(new Node(id), new ArrayList<Edge>());
    }

    public void removeNode(Node n) {
        adjEdList.remove(n);
        for(List<Edge> neighbors : adjEdList.values()) {
            List<Edge> neighborsCopy = List.copyOf(neighbors);
            for(Edge edge : neighborsCopy) {
                if(edge.to().equals(n) || edge.from().equals(n))
                    neighbors.remove(edge);
            }
        }
    }

    public void removeNode(int id) {
        removeNode(getNode(id));
    }

    public List<Node> getSuccessors(Node n) {
        List<Node> result = new ArrayList<>();

        for(Edge e : adjEdList.get(n)) {
            Node to = e.to();
            if(e.from().equals(n) && !adjEdList.containsValue(to))
                result.add(to);
        }

        return result;
    }

    public List<Node> getSuccessors(int id) {
        return getSuccessors(getNode(id));
    }

    public boolean adjacent(Node u, Node v) {

        for(Edge e : adjEdList.get(u)) {
            if(e.to().equals(v))
                return true;
        }

        for(Edge e : adjEdList.get(v)) {
            if(e.to().equals(u))
                return true;
        }

        return false;
    }

    public boolean adjacent(int idU, int idV) {
        return adjacent(getNode(idU), getNode(idV));
    }

    public List<Node> getAllNodes() {
        List<Node> result = new ArrayList<>();
        result.addAll(adjEdList.keySet());
        return result;
    }

    public int largestNodeId() {
        List<Node> allNodes = getAllNodes();
        int maxId = allNodes.get(0).getId();

        for(int i=1; i< allNodes.size(); i++) {
            int id = allNodes.get(i).getId();
            if(id > maxId) maxId = id;
        }

        return maxId;
    }

    public int nbEdges() {
        List<Edge> counted = new ArrayList<>();
        int result = 0;

        for(Map.Entry<Node, List<Edge>> entry : adjEdList.entrySet()) {
            for(Edge e : entry.getValue()) {
                if(!counted.contains(e)) {
                    result++;
                    counted.add(e);
                }
            }
        }

        return result;
    }

    public boolean existsEdge(Node u, Node v) {
        return adjEdList.containsValue(new Edge(u, v));
    }

    public boolean existsEdge(int idU, int idV) {
        return adjEdList.containsValue(new Edge(idU, idV));
    }

    public void addEdge(Node from, Node to) {
        if(existsNode(from) && existsNode(to)) {
            adjEdList.get(from).add(new Edge(from, to));
        }
    }

    public void addEdge(int fromId, int toId) {
        if(existsNode(fromId) && existsNode(toId)) { // TODO this condition is checked twice (because it is checked in the call to addEdge(Node, Node))
            addEdge(getNode(fromId), getNode(toId));
        }
    }

    public void removeEdge(Node from, Node to) {

    }

    public void removeEdge(int fromId, int toId) {

    }

    public  List<Edge> GetOutEdges(Node n){
        return null;
    }

    public List<Edge> GetIntIncidentEdges(Node n){
        return null;
    }

    public List<Edge> getAllEdges(){
        return null;
    }

    public int inDegree(Node n){
        return -1;
    }

    public int outDegree(Node n){
        return -1;
    }

    public int degree(Node n){
        return -1;
    }

    public int[] toSuccessorArray(){
        return null;
    }

    public int[][] toAdjMatrix(){
        return null;
    }

    public Graf getReverse(){
        return null;
    }

    public Graf getTransitiveClosure(){
        return null;
    }

    public List<Node> getDFS(){
        return null;
    }

    public List<Node> getBFS(){
        return null;
    }

    public String toDotString(){
        return null;
    }

    public void toDotFile(String fileName){

    }

}
