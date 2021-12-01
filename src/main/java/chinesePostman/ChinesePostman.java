package chinesePostman;

import chinesePostman.paireWiseMatchingStrategy.PairWiseMatchingStrategy;
import m1graf2021.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChinesePostman {
    UndirectedGraf graph;
    String sourceDotString;
    List<Edge> duplicatedEdge;

    public ChinesePostman(UndirectedGraf graph) {
        this.graph = graph;
    }

    public ChinesePostman(UndirectedGraf graph, String sourceDotString) {
        this.graph = graph;
        this.sourceDotString = sourceDotString;
    }

    public EulerianEnum isEulerian() {
        int nbOddDegreeNodes = nbOddDegree();

        if(nbOddDegreeNodes == 0) return EulerianEnum.Eulerian;
        if(nbOddDegreeNodes == 2) return EulerianEnum.SemiEulerian;
        else return EulerianEnum.NotEulerian;
    }

    public int nbOddDegree() {
        int nbOddDegreeNodes = 0;

        for(Node node : graph.getAllNodes()) {
            int degree = graph.degree(node);

            if((degree % 2) == 1) nbOddDegreeNodes++;
        }

        return nbOddDegreeNodes;
    }

    public List<Pair<Node, Integer>> getDegreeForEachNode() {
        return null;
    }

    public List<Edge> computeChineseCircuit(PairWiseMatchingStrategy pairWiseMatchingStrategy) {
        if(!(isEulerian() == EulerianEnum.Eulerian)) {
            List<Edge> edgesToAdd = pairWiseMatchingStrategy.getEdgesToDuplicate(graph);
            duplicatedEdge = edgesToAdd;
            for(Edge edge : edgesToAdd) {
                graph.addEdge(edge.from(), edge.to(), edge.getWeight());
            }
        }

        Node startNode = graph.getNode(1);

        UndirectedGraf graphCopy = graph.copy();

        return getEulerianCircuit(startNode, graphCopy);
    }

    private List<Edge> getEulerianCircuit(Node startNode, UndirectedGraf graph) {
        List<Edge> result = new ArrayList<>();

        List<Edge> incidentEdges = graph.getIncidentEdges(startNode);

        if(incidentEdges.isEmpty()) return result;

        while(!incidentEdges.isEmpty()) {
            Edge firstIncidentEdge = null;
            for(Edge edge : incidentEdges) {
                if(edge.from().equals(startNode)){
                    firstIncidentEdge = edge;
                    break;
                }
            }
            if(firstIncidentEdge == null){

            }

            result.add(firstIncidentEdge);
            graph.removeEdge(firstIncidentEdge.from(), firstIncidentEdge.to());

            startNode = firstIncidentEdge.to();
            incidentEdges = graph.getIncidentEdges(startNode);
        }

        List<Edge> result2 = new ArrayList<>();
        for(Edge edge : result) {
            List<Edge> resultFromNode = getEulerianCircuit(edge.from(), graph);
            result2.addAll(resultFromNode);
        }

        return result2;
    }

    public static ChinesePostman fromDotFile(String path) {
        return null;
    }

    public static ChinesePostman fromDotString(String dotString) {
        return null;
    }

    public String toDotString() {
        return null;
    }

    public void toDotFile(String path) {

    }
}
