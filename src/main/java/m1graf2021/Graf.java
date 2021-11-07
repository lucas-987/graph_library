package m1graf2021;

import java.util.*;

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

    private Graf(Map<Node, List<Edge>> adjEdList) {
        this.adjEdList = adjEdList;
    }

    private Graf(int[][] adjacencyMatrix) {
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
        adjEdList.put(n, new ArrayList<>());
    }

    public void addNode(int id) {
        adjEdList.put(new Node(id), new ArrayList<>());
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
        int[] successorArray = new int[(adjEdList.size() - 1) + nbEdge];

        int cursor = 0;
        for(int i=0; i<adjEdList.size(); i++) {
            Node node = getNode(i+1);
            if(node.getId() != 1 && node.getId() != 0) {
                successorArray[cursor] = 0;
                cursor++;
            }

            for(Edge edge : adjEdList.get(node)) {
                successorArray[cursor] = edge.to().getId();
                cursor++;
            }
        }

        return successorArray;
    }

    public int[][] toAdjMatrix(){

        int matrix [][] = new int[this.nbNodes()][this.nbNodes()];
        for (int i = 0 ; i < matrix.length;i++ ){
            for (int y = 0 ; y< matrix.length;y++){
                matrix[i][y] = 0;
            }//la je parcoure la matrice et je met des zero
        }

        for(Map.Entry entry : this.adjEdList.entrySet()){
            List<Edge> list = (List)entry.getValue();
            Node node= (Node)  entry.getKey();
            int k = node.getId() - 1;
            for (Edge e: list){
                matrix[k][e.to().getId() - 1] += 1;// la je parcour la la map ou jsp quoi et je met des 1 :)
            }
        }

        return matrix;

    }//moi

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

    /* les deux algorithme sont ecrit exactement de la meme dacon come dans le cours jsp esq c'est la meilleur solution
    * par exemple le DFS est en deux partie
    * si t'arrive pas a le comprendre va sur le cours
    * */
    public List<Node> getDFS(){
        boolean visited[] = new boolean[getAllNodes().size()];
        Node node = (Node) adjEdList.keySet();
        List<Node> list = new LinkedList<>();
        list = DFSUtil(node.getId(),visited);
        return list;
    }//moi

    private List<Node> DFSUtil(int v, boolean visited[]){
        List<Node> listB = new LinkedList<>();
        visited[v] = true;
        Iterator<Edge> i = this.adjEdList.get(getNode(v)).listIterator();
        while (i.hasNext()){
            int n = i.next().to().getId();
            if (!visited[n]) {
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
        Node node = getNode(1);
        visited[node.getId() - 1] = true;
        q.add(node);

        while (q.size() != 0){
            Node s = q.poll();
            Iterator<Edge> i = this.adjEdList.get(s).listIterator();
            while (i.hasNext()){
                int n = i.next().to().getId();
                if (!visited[n-1]){
                    visited[n-1] =true;
                    q.add(getNode(n));
                }
            }
        }
        return q;
    }//moi

    public String toDotString(){
        return null;
    }

    public void toDotFile(String fileName){
    }

    public static Graf fromDotFile(String path) {
        return null;
    }

    public static Graf fromDotString(String dotString) {
        return null;
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
                    & edgesNodeGraf.size() == edgesNodeGrafParam.size()
                    & edgesNodeGraf.containsAll(edgesNodeGrafParam) & edgesNodeGrafParam.containsAll(edgesNodeGraf);

            if(!grafsAreEquals) return false;
        }

        return grafsAreEquals;
    }
}
