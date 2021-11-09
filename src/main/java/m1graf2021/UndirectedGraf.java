package m1graf2021;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedGraf extends Graf {

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

    private int getNbEdge(Edge edge) {
        if(!existsEdge(edge.from(), edge.to())) return 0;

        int nbEdge = 0;
        for(Edge edgeIterator : adjEdList.get(edge.from())) {
            if(edgeIterator.from().equals(edge.from()) && edgeIterator.to().equals(edge.to()))
                nbEdge++;
        }

        return nbEdge;
    }

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

    @Override
    public List<Node> getSuccessors(int id) {
        return getSuccessors(getNode(id));
    }

    @Override
    public int nbEdges() {
        return super.nbEdges() / 2;
    }

    @Override
    public boolean existsEdge(Node u, Node v) {
        if(super.existsEdge(u, v))
            return true;
        if(super.existsEdge(v, u))
            return true;

        return false;
    }

    @Override
    public boolean existsEdge(int idU, int idV) {
        if(super.existsEdge(idU, idV))
            return true;
        if(super.existsEdge(idV, idU))
            return true;

        return false;
    }

    @Override
    public List<Edge> getOutEdges(Node n) {
        return getIncidentEdges(n);
    }

    @Override
    public List<Edge> getOutEdges(int nodeId) {
        if(existsNode(nodeId))
            return getOutEdges(getNode(nodeId));
        else
            return new ArrayList<>();
    }

    @Override
    public List<Edge> getInEdges(Node n) {
        return getIncidentEdges(n);
    }

    @Override
    public List<Edge> getInEdges(int nodeId) {
        if(existsNode(nodeId))
            return getInEdges(getNode(nodeId));
        else
            return new ArrayList<>();
    }

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

    @Override
    public List<Edge> getIncidentEdges(int nodeId) {
        if(!existsNode(nodeId)) return new ArrayList<>();
        return super.getIncidentEdges(nodeId);
    }

    @Override
    public int inDegree(Node n) {
        return degree(n);
    }

    @Override
    public int outDegree(Node n) {
        return degree(n);
    }

    @Override
    public int degree(Node n) {
        return super.degree(n) / 2;
    }

    @Override
    public Graf getReverse() {
        return this;
    }

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


}
