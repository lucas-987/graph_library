package m1graf2021;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedGraf extends Graf {

    /**
     * Create a Graf from an int[] corresponding to a successor array of a graph
     * @param number, the array corresponding to a successor array
     */
    public UndirectedGraf(int... number) {
        super(number);
        List<Edge> toAdd = new ArrayList<>();
        for(List<Edge> edges : adjEdList.values()) {
            for(Edge edge : edges) {
                int nbEdgeInGraf = getNbEdge(edge);
                int nbSymetricEdgeInGraf = getNbEdge(edge.getSymmetric());
                int deltaEdgeSymetric = nbEdgeInGraf - nbSymetricEdgeInGraf;
                if(deltaEdgeSymetric < 0) {
                    for(int i=deltaEdgeSymetric; i<0; i++)
                    adjEdList.get(edge.from()).add(edge);
                }
                else if(deltaEdgeSymetric > 0) {
                    for(int i=0; i<deltaEdgeSymetric; i++)
                        adjEdList.get(edge.to()).add(edge.getSymmetric());
                }
            }
        }
    }

    /**
     * Create a Graf from an adjacency list
     * @param adjEdList, a Map<Node, List<Edge>> describing the graph
     */
    public UndirectedGraf(Map<Node, List<Edge>> adjEdList) {
        super(adjEdList);
        List<Edge> toAdd = new ArrayList<>();
        for(List<Edge> edges : adjEdList.values()) {
            for(Edge edge : edges) {
                int nbEdgeInGraf = getNbEdge(edge);
                int nbSymetricEdgeInGraf = getNbEdge(edge.getSymmetric());
                int deltaEdgeSymetric = nbEdgeInGraf - nbSymetricEdgeInGraf;
                if(deltaEdgeSymetric < 0) {
                    for(int i=deltaEdgeSymetric; i<0; i++)
                        adjEdList.get(edge.from()).add(edge);
                }
                else if(deltaEdgeSymetric > 0) {
                    for(int i=0; i<deltaEdgeSymetric; i++)
                        adjEdList.get(edge.to()).add(edge.getSymmetric());
                }
            }
        }
    }

    /**
     * A constructor creating a graph corresponding to the adjacency matrix given in parameter
     * @param adjacencyMatrix, an int[][] representing the adjacency matrix of the graph to create
     */
    public UndirectedGraf(int[][] adjacencyMatrix) {
        super(adjacencyMatrix);
        List<Edge> toAdd = new ArrayList<>();
        for(List<Edge> edges : adjEdList.values()) {
            for(Edge edge : edges) {
                int nbEdgeInGraf = getNbEdge(edge);
                int nbSymetricEdgeInGraf = getNbEdge(edge.getSymmetric());
                int deltaEdgeSymetric = nbEdgeInGraf - nbSymetricEdgeInGraf;
                if(deltaEdgeSymetric < 0) {
                    for(int i=deltaEdgeSymetric; i<0; i++)
                        adjEdList.get(edge.from()).add(edge);
                }
                else if(deltaEdgeSymetric > 0) {
                    for(int i=0; i<deltaEdgeSymetric; i++)
                        adjEdList.get(edge.to()).add(edge.getSymmetric());
                }
            }
        }
    }

    /**
     *
     * @param edge
     * @return the numbre of the edge given in paramater in the graf
     */
    private int getNbEdge(Edge edge) {
        if(!existsEdge(edge.from(), edge.to())) return 0;

        int nbEdge = 0;
        for(Edge edgeIterator : adjEdList.get(edge.from())) {
            if(edgeIterator.from().equals(edge.from()) && edgeIterator.to().equals(edge.to()))
                nbEdge++;
        }

        return nbEdge;
    }
//

    /**
     * Add an edge to the graph going from the param "from" to the param "to"
     * @param from, the source node of the edge
     * @param to, the destination node of the edge
     */
    @Override
    public void addEdge(Node from, Node to) {
        super.addEdge(from, to);
        super.addEdge(to, from);
    }

    /**
     * Add an edge to the graph going from the node corresponding to "fromId" to the node corresponding to "toId"
     * @param fromId, the id of the source node of the edge
     * @param toId, the id of the destination node of the edge
     */
    @Override
    public void addEdge(int fromId, int toId) {
        super.addEdge(fromId, toId);
        super.addEdge(toId, fromId);
    }

    /**
     * Add an edge to the graph going from the param "from" to the param "to"
     * @param from, the source node of the edge
     * @param to, the destination node of the edge
     * @param weight, the weight of the edge
     */
    @Override
    public void addEdge(Node from, Node to, Integer weight) {
        super.addEdge(from, to, weight);
        super.addEdge(to, from, weight);
    }

    /**
     * Add an edge to the graph going from the node corresponding to "fromId" to the node corresponding to "toId"
     * @param fromId, the id of the source node of the edge
     * @param toId, the of the destination node of the edge
     * @param weight, the weight of the edge
     */
    @Override
    public void addEdge(int fromId, int toId, Integer weight) {
        super.addEdge(fromId, toId, weight);
        super.addEdge(toId,fromId, weight);
    }

    /**
     * Remove the edge defined by from and to
     * @param from, the source node of the edge
     * @param to, the destination node of the edge
     */
    @Override
    public void removeEdge(Node from, Node to) {
        super.removeEdge(from, to);
        super.removeEdge(to, from);
    }

    /**
     * Remove the edge defined by fromId and toId
     * @param fromId, the id of the source node of the edge
     * @param toId, the id of the destination of the edge
     */
    @Override
    public void removeEdge(int fromId, int toId) {
        super.removeEdge(fromId, toId);
        super.removeEdge(toId, fromId);
    }

    /**
     * Get the successors/neighbours of a node
     * @param n, the node
     * @return a List<Node> containing the result
     */
    @Override
    public List<Node> getSuccessors(Node n) {
        List<Node> result = new ArrayList<>();

        for(List<Edge> edges : adjEdList.values()) {
            for(Edge edge : edges) {
                if(edge.from().equals(n)) {
                    if(!result.contains(edge.to()))
                        result.add(edge.to());
                }
                else if(edge.to().equals(n)) {
                    if(!result.contains(edge.from()))
                        result.add(edge.from());
                }
            }
        }

        return result;
    }

    /**
     * Get the successors/neighbours of a node
     * @param id, the id of the node
     * @return a List<Node> containing the result
     */
    @Override
    public List<Node> getSuccessors(int id) {
        return getSuccessors(getNode(id));
    }

    /**
     *
     * @return The number of edges in the node
     */
    @Override
    public int nbEdges() {
        return super.nbEdges() / 2;
    }

    /**
     *
     * @param u, a node
     * @param v, another node
     * @return True if the edge between u and v exists
     */
    @Override
    public boolean existsEdge(Node u, Node v) {
        if(super.existsEdge(u, v))
            return true;
        if(super.existsEdge(v, u))
            return true;

        return false;
    }

    /**
     *
     * @param idU, the id of a node
     * @param idV, the id of another node
     * @return True if there is an edge between idU and idV
     */
    @Override
    public boolean existsEdge(int idU, int idV) {
        if(super.existsEdge(idU, idV))
            return true;
        if(super.existsEdge(idV, idU))
            return true;

        return false;
    }

    /**
     *
     * @param n, a node
     * @return A list containing the out edges of n
     */
    @Override
    public List<Edge> getOutEdges(Node n) {
        return getIncidentEdges(n);
    }

    /**
     *
     * @param nodeId, the id of a node
     * @return A list containing the out edges of the node defined by nodeId
     */
    @Override
    public List<Edge> getOutEdges(int nodeId) {
        if(existsNode(nodeId))
            return getOutEdges(getNode(nodeId));
        else
            return new ArrayList<>();
    }

    /**
     *
     * @param n, a node
     * @return A list containing the in edges of n
     */
    @Override
    public List<Edge> getInEdges(Node n) {
        return getIncidentEdges(n);
    }

    /**
     *
     * @param nodeId, the id of a node
     * @return A list containing the in edges of the node defined by nodeId
     */
    @Override
    public List<Edge> getInEdges(int nodeId) {
        if(existsNode(nodeId))
            return getInEdges(getNode(nodeId));
        else
            return new ArrayList<>();
    }

    /**
     *
     * @param n, a node
     * @return A list containing the incident edges of n
     */
    @Override
    public List<Edge> getIncidentEdges(Node n) {
        List<Edge> result = new ArrayList<>();

        for(List<Edge> edges : adjEdList.values()) {
            for(Edge edge : edges) {
                if(edge.from().equals(n)) {
                        result.add(edge);
                }
                else if(edge.to().equals(n)) {
                        result.add(edge);
                }
            }
        }

        return result;
    }

    /**
     *
     * @param nodeId, the id of a node
     * @return A list containing the incident edges of the node defined by nodeId
     */
    @Override
    public List<Edge> getIncidentEdges(int nodeId) {
        if(!existsNode(nodeId)) return new ArrayList<>();
        return getIncidentEdges(getNode(nodeId));
    }

    /**
     *
     * @param n, a node
     * @return The indegree of n
     */
    @Override
    public int inDegree(Node n) {
        return degree(n);
    }

    /**
     *
     * @param n, a node
     * @return The outdegree of n
     */
    @Override
    public int outDegree(Node n) {
        return degree(n);
    }

    /**
     *
     * @param n, a node
     * @return The degree of n
     */
    @Override
    public int degree(Node n) {
        return super.degree(n) / 2;
    }

    /**
     *
     * @return A Graf corresponding to the transpose of the graph
     */
    @Override
    public Graf getReverse() {
        return this;
    }

    /**
     *
     * @return A string describing the graph in the dot format
     */
    @Override
    public String toDotString() {
        String result = "";

        boolean directed = !(this instanceof UndirectedGraf);

        result += (directed ? "digraph" : "graph") + " {\n";

        HashMap<Node, List<Edge>> adjEdListCopy = new HashMap<>();
        adjEdListCopy.putAll(adjEdList);

        for(Node node : this.getAllNodes()) {
            for(Edge edge : adjEdListCopy.get(node)) {
                result += "    " + edge.from().getId() + " " + (directed ? "->" : "--") + " " + edge.to().getId();

                Integer weight = edge.getWeight();
                if(weight != null) {
                    result += " [len=" + weight + ", label=" + weight + "]";
                }

                result += "\n";
                adjEdListCopy.get(edge.to()).remove(edge.getSymmetric());
            }
        }

        result += "}";

        return result;
    }

    /**
     * Write a description of the graph in the dot format in the file given in parameters
     * @param fileName, the path to the file (it will be created if the file doesn't exist), the extension needs to be provided
     */
    @Override
    public void toDotFile(String fileName) {
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

    public UndirectedGraf copy() {
        // TODO new
        UndirectedGraf copy = new UndirectedGraf();
        copy.adjEdList = getAdjEdList();
        return copy;
    }
}
