package m1graf2021;

import java.util.List;
import java.util.Map;

public class Graf {
    Map<Node, List<Edge>> adjEdList;

    public Graf() {

    };

    public Graf(int... number) {

    }

    public int nbNodes() {

    }

    public boolean existsNode(Node n) {

    }

    public boolean existsNode(int id) {

    }

    public Node getNode(int id) {

    }

    public void addNode(Node n) {

    }

    public void addNode(int id) {

    }

    public void removeNode(Node n) {

    }

    public void removeNode(int id) {

    }

    public List<Node> getSuccessors(Node n) {

    }

    public List<Node> getSuccessors(int id) {

    }

    public boolean adjacent(Node u, Node v) {

    }

    public boolean adjacent(int idU, int idV) {

    }

    public List<Node> getAllNodes() {

    }

    public int largestNodeId() {

    }

    public int nbEdges() {

    }

    public boolean existsEdge(Node u, Node v) {

    }

    public boolean existsEdge(int idU, int idV) {

    }

    public void addEdge(Node from, Node to) {

    }

    public void addEdge(int fromId, int toId) {

    }

    public void removeEdge(Node from, Node to) {

    }

    public void removeEdge(int fromId, int toId) {

    }

    public  List<Edge> GetOutEdges(Node n){

    }

    public List<Edge> GetIntIncidentEdges(Node n){

    }

    public List<Edge> getAllEdges(){

    }

    public int inDegree(Node n){

    }

    public int outDegree(Node n){

    }

    public int degree(Node n){

    }

    public int[] toSuccessorArray(){

    }

    public int[][] toAdjMatrix(){

    }

    public Graf getReverse(){

    }

    public Graf getTransitiveClosure(){

    }

    public List<Node> getDFS(){

    }

    public List<Node> getBFS(){

    }

    public String toDotString(){

    }

    public void toDotFile(String fileName){

    }

}
