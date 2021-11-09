package m1graf2021;

import javax.sound.sampled.AudioFormat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graf {
    Map<Node, List<Edge>> adjEdList;

    public Graf() {
        this.adjEdList = new HashMap<>();
    }

    public Graf(int... number) {
        this.adjEdList = new HashMap<>();

        if(number.length > 0) {
            //noeud1
            int currentNodeId = 1;
            this.adjEdList.put(new Node(currentNodeId), new ArrayList<>());

            for(int i=0; i< number.length; i++) {
                int val = number[i];

                // We also test that the 0 is not the final 0
                // in which case we shouldn't create a new node
                if(val == 0 && i != (number.length - 1)) {
                    currentNodeId++;
                    this.adjEdList.put(new Node(currentNodeId), new ArrayList<>());
                }
                else if(val != 0){ // in order to prevent the final 0 to be processed in this block
                    this.adjEdList.get(getNode(currentNodeId)).add(new Edge(currentNodeId, val));
                }
            }
        }
    }

    public Graf(Map<Node, List<Edge>> adjEdList) {
        this.adjEdList = adjEdList;
    }

    public Graf(int[][] adjacencyMatrix) {
        this.adjEdList = new HashMap<>();

        int nbNodes = adjacencyMatrix.length;

        for(int i = 0; i<nbNodes; i++){
            Node node = new Node(i+1);
            this.adjEdList.put(node, new ArrayList<>());

            for(int j = 0; j<nbNodes; j++) {
                int nbEdgeToJ = adjacencyMatrix[i][j];

                if(nbEdgeToJ == 1) {
                    this.adjEdList.get(node).add(new Edge(node.getId(), j+1));
                }
                else if(nbEdgeToJ > 1) {
                    for(int k = 0; k<nbEdgeToJ; k++) {
                        this.adjEdList.get(node).add(new Edge(node.getId(), j+1));
                    }
                }
            }
        }
    }

    /**
     * For testing purpose only
     * @return
     */
    public Map<Node, List<Edge>> getAdjEdList() {
        return this.adjEdList;
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
        if(!existsNode(n)) {
            List<Node> allNodes = getAllNodes();
            Collections.sort(allNodes);

            int lastNodeId;
            if(allNodes.size() > 0) {
                lastNodeId = allNodes.get(allNodes.size() -1).getId();
            }
            else {
                lastNodeId = 1;
            }

            if(n.getId() - lastNodeId > 1) {
                for(int i = lastNodeId; i<n.getId(); i++) {
                    adjEdList.put(new Node(i), new ArrayList<>());
                }
            }
            adjEdList.put(n, new ArrayList<>());
        }
    }

    public void addNode(int id) {
        addNode(new Node(id));
    }

    public void removeNode(Node n) {
        adjEdList.remove(n);
        for(List<Edge> neighbors : adjEdList.values()) {
            List<Edge> neighborsCopy = new ArrayList<>();
            Collections.copy(neighborsCopy, neighbors);
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

        for(List<Edge> edges : adjEdList.values()) {
            for(Edge e : edges) {
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

    public void addEdge(Node from, Node to, Integer weight) {
        if(existsNode(from) && existsNode(to)) {
            if(weight == null)
                adjEdList.get(from).add(new Edge(from, to));
            else
                adjEdList.get(from).add(new Edge(from, to, weight));
        }
    }

    public void addEdge(int fromId, int toId, Integer weight) {
        if(existsNode(fromId) && existsNode(toId)) { // TODO this condition is checked twice (because it is checked in the call to addEdge(Node, Node))
            addEdge(getNode(fromId), getNode(toId), weight);
        }
    }

    public void removeEdge(Node from, Node to) {
        List<Edge> successorsEdge = adjEdList.get(from);

        if(successorsEdge != null) {
            for(Edge edge : successorsEdge) {
                if(edge.to().equals(to))
                    successorsEdge.remove(edge);
            }
        }
    }

    public void removeEdge(int fromId, int toId) {
        Node from = getNode(fromId);
        Node to = getNode(toId);

        if(from != null && to != null)
            removeEdge(from, to);
    }

    public  List<Edge> getOutEdges(Node n){
        if(existsNode(n))
            return adjEdList.get(n);
        else return new ArrayList<>();
    }

    public List<Edge> getOutEdges(int nodeId) {
        if(existsNode(nodeId)) {
            return getOutEdges(getNode(nodeId));
        }
        else return new ArrayList<>();
    }

    public List<Edge> getInEdges(Node n) {
        List<Edge> result = new ArrayList<>();

        if(!existsNode(n)) return result;

        for(List<Edge> listEdge : adjEdList.values()) {
            for(Edge edge : listEdge) {
                if(edge.to().equals(n)) {
                    if(!result.contains(edge))
                        result.add(edge);
                }
            }
        }

        return result;
    }

    public List<Edge> getInEdges(int nodeId) {
        if(existsNode(nodeId)) {
            return getInEdges(getNode(nodeId));
        }
        else return new ArrayList<>();
    }

    public List<Edge> getIncidentEdges(Node n){
        List<Edge> result = new ArrayList<>();

        if(!existsNode(n)) return result;

        for(List<Edge> listEdge : adjEdList.values()) {
            for(Edge edge : listEdge) {
                if(edge.to().equals(n) || edge.from().equals(n)) {
                    if(!result.contains(edge))
                        result.add(edge);
                }
            }
        }

        return result;
    }

    public List<Edge> getIncidentEdges(int nodeId) {
        if(existsNode(nodeId)) {
            return getIncidentEdges(getNode(nodeId));
        }
        else return new ArrayList<>();
    }

    public List<Edge> getAllEdges(){
        List<Edge> result = new ArrayList<>();

        for(List<Edge> listEdge : adjEdList.values()) {
            result.addAll(listEdge);
        }

        return result;
    }

    public int inDegree(Node n){
        if(!existsNode(n)) return -1;

        int result = 0;

        for(List<Edge> listEdge : adjEdList.values()) {
            for(Edge edge : listEdge) {
                if(edge.to().equals(n)) {
                    result++;
                }
            }
        }

        return result;
    }

    public int outDegree(Node n){
        if(!existsNode(n)) return -1;

        int result = 0;

        for(Edge edge : adjEdList.get(n)) {
            if(edge.from().equals(n))
                result++;
        }

        return result;
    }

    public int degree(Node n){
        if(!existsNode(n)) return -1;

        int result = 0;

        for(List<Edge> listEdge : adjEdList.values()) {
            for(Edge edge : listEdge) {
                if(edge.to().equals(n) || edge.from().equals(n)) {
                    if(edge.isSelfLoop())
                        result += 2;
                    else
                        result++;
                }
            }
        }

        return result;
    }

    public int[] toSuccessorArray(){
        int nbEdge = 0;
        for(List<Edge> edges : adjEdList.values()) {
            nbEdge += edges.size();
        }

        // TODO test length of successorArray
        int[] successorArray = new int[(adjEdList.size()) + nbEdge];

        int cursor = 0;
        for(int i=0; i<adjEdList.size(); i++) {
            Node node = getNode(i+1);

            for(Edge edge : adjEdList.get(node)) {
                successorArray[cursor] = edge.to().getId();
                cursor++;
            }

            successorArray[cursor] = 0;
            cursor++;
        }

        return successorArray;
    }

    public int[][] toAdjMatrix(){

        int matrix [][] = new int[this.nbNodes()][this.nbNodes()];
        for (int i = 0 ; i < matrix.length;i++ ){
            for (int y = 0 ; y< matrix.length;y++){
                matrix[i][y] = 0;
            }
        }

        for(Map.Entry entry : this.adjEdList.entrySet()){
            List<Edge> list = (List)entry.getValue();
            Node node= (Node)  entry.getKey();
            int k = node.getId() - 1;
            for (Edge e: list){
                matrix[k][e.to().getId() - 1] += 1;
            }
        }

        return matrix;

    }

    public Graf getReverse(){
        Map<Node, List<Edge>> reverseAdjList = new HashMap<>();

        for(Node node : adjEdList.keySet()) {
            reverseAdjList.put(node, new ArrayList<>());
        }

        for(List<Edge> edges : adjEdList.values()) {
            for(Edge edge : edges) {
                Edge symmetric = edge.getSymmetric();
                reverseAdjList.get(symmetric.from()).add(symmetric);
            }
        }

        return new Graf(reverseAdjList);
    }

    public Graf getTransitiveClosure(){
        int[][] result = toAdjMatrix();

        int nbNodes = nbNodes();

        for (int k = 0; k < nbNodes; k++)
        {
            // Pick all vertices as source one by one
            for (int i = 0; i < nbNodes; i++)
            {
                // Pick all vertices as destination for the
                // above picked source
                for (int j = 0; j < nbNodes; j++)
                {
                    // If vertex k is on a path from i to j,
                    // then make sure that the value of reach[i][j] is 1
                    result[i][j] = (result[i][j]!=0) ||
                            ((result[i][k]!=0) && (result[k][j]!=0))?1:0;
                }
            }
        }

        return new Graf(result);
    }


    public List<Node> getDFS(){
        boolean visited[] = new boolean[getAllNodes().size()];
        List<Node> list  = new LinkedList<>() ;


        list = DFSUtil(1,visited);
        while (!list.containsAll(getAllNodes())){
              for(Node node1 : getAllNodes()){
                  if (!list.contains(node1)){
                     list.addAll(DFSUtil(node1.getId(),visited));
                     break;
                  }
              }
        }
        return list;

    }

    private List<Node> DFSUtil(int v, boolean visited[]){

        List<Node> listB = new LinkedList<>();
        visited[v-1] = true;
        Iterator<Edge> i = this.adjEdList.get(getNode(v)).listIterator();
        while (i.hasNext()){
            int n = i.next().to().getId();
            if (!visited[n-1]) {
                List<Node> b =   DFSUtil(n,visited);
                listB.addAll(b);
            }
        }

        listB.add(getNode(v));
        return listB;
    }





    public List<Node> getBFS() {
        boolean visited[] = new boolean[getAllNodes().size()];
        LinkedList<Node> q =  new LinkedList<Node>();
        LinkedList<Node> resutl =  new LinkedList<Node>();
        Node node = getNode(1);
        q.add(node);
        visited[node.getId() - 1] = true;
        resutl.add(node);
        while (q.size() != 0 || !resutl.containsAll(getAllNodes())){
            if (q.size() == 0 && !resutl.containsAll(getAllNodes())){
                for (Node node1 : getAllNodes()){
                    if (resutl.contains(node1) ){
                        q.add(node1);
                        break;
                    }
                }
            }
                Node s = q.poll();
                Iterator<Edge> i  = this.adjEdList.get(s).listIterator();
                while (i.hasNext()){
                int n = i.next().to().getId();
                if (!visited[n-1]){
                    visited[n-1] =true;
                    q.add(getNode(n));
                    resutl.add(getNode(n));

                }
            }
        }
        return resutl;
    }




    public String toDotString(){
        String result = "";

        boolean directed = !(this instanceof UndirectedGraf);

        result += (directed ? "digraph" : "graph") + " {\n";

        for(Node node : this.getAllNodes()) {
            for(Edge edge : adjEdList.get(node)) {
                result += "    " + edge.from().getId() + " " + (directed ? "->" : "--") + " " + edge.to().getId();

                Integer weight = edge.getWeight();
                if(weight != null) {
                    result += " [len=" + weight + ", label=" + weight + "]";
                }

                result += "\n";
            }
        }

        result += "}";

        return result;
    }

    public void toDotFile(String fileName){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fileName + ".gv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String dotString = toDotString();
        pw.print(dotString);
        pw.close();
    }

    public static Graf fromDotFile(String path) {
        Graf result = null;
        try{
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            String dotString = new String(encoded, StandardCharsets.UTF_8);
            result = fromDotString(dotString);
        }
        catch (IOException e) {
        }
        return result;
    }

    public static Graf fromDotString(String dotString) {
        String lines[] = dotString.split("\\R+");

        Graf graf = null;

        for(int i=0; i< lines.length; i++) {
            String line = lines[i];
            line = line.trim();
            line.replaceAll("\\s+"," ");

            if(line.charAt(0) != '#') {
                String words[] = line.split(" ");

                switch (words[0]) {
                    case "digraph":
                        if (graf == null) graf = new Graf();
                        break;
                    case "graph":
                        if (graf == null) graf = new UndirectedGraf();
                        break;
                    default:
                        if (graf != null) {
                            if (words[0].matches("\\d+")) {
                                int fromId = Integer.valueOf(words[0]);
                                if (!graf.existsNode(fromId))
                                    graf.addNode(fromId);

                                if (words.length >= 3) {
                                    if (words[1].equals("->") || words[1].equals("--")) {
                                        String toIds[] = words[2].replaceAll(";", "").split(",");
                                        for (String toIdString : toIds) {
                                            if (toIdString.matches("\\d+")) {
                                                int toId = Integer.valueOf(toIdString);
                                                if (!graf.existsNode(toId))
                                                    graf.addNode(toId);

                                                Integer weight = null;
                                                if (words.length >= 4) {
                                                    Matcher matcher = Pattern.compile("\\[(len|label)=-?\\d+(,|\\])").matcher(words[3]);
                                                    if(matcher.find()) {
                                                        String weightWord = matcher.group(0);
                                                        Matcher matcherInt = Pattern.compile("-?\\d+").matcher(weightWord);

                                                        if(matcherInt.find()) {
                                                            weight = Integer.valueOf(matcherInt.group(0));
                                                        }
                                                    }
                                                }
                                                graf.addEdge(fromId, toId, weight);
                                                if(graf instanceof UndirectedGraf)
                                                    graf.addEdge(toId, fromId, weight);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            }
        }
        return graf;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Graf)) return false;

        Graf grafParam = (Graf) obj;

        if(this.nbNodes() != grafParam.nbNodes()) return false;

        boolean grafsAreEquals = true;
        for(int i=0; i< this.nbNodes(); i++) {
            Node nodeGraf = this.getNode(i+1);
            Node nodeGrafParam = grafParam.getNode(i+1);

            List<Edge> edgesNodeGraf = this.adjEdList.get(nodeGraf);
            List<Edge> edgesNodeGrafParam = grafParam.getAdjEdList().get(nodeGrafParam);

            // &= nodes are equals and list of arrays are equals
            grafsAreEquals &= nodeGraf.equals(nodeGrafParam)
                    & edgesNodeGraf.size() == edgesNodeGrafParam.size();

            if(!grafsAreEquals) return false;

            for(int j=0; j<edgesNodeGraf.size(); j++) {
                Edge edgeNodeGraf = edgesNodeGraf.get(j);
                Edge edgeNodeGrafParam = edgesNodeGrafParam.get(j);
                if(!edgeNodeGraf.equals(edgeNodeGrafParam)) return false;
            }
        }

        return grafsAreEquals;
    }
}
