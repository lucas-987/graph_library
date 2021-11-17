package chinesePostman;

import chinesePostman.paireWiseMatchingStrategy.PairWiseMatchingStrategy;
import m1graf2021.*;

import java.util.List;

public class ChinesePostman {
    UndirectedGraf graph;
    String sourceDotString;

    public ChinesePostman(UndirectedGraf graph) {
        this.graph = graph;
    }

    public ChinesePostman(UndirectedGraf graph, String sourceDotString) {
        this.graph = graph;
        this.sourceDotString = sourceDotString;
    }

    public EulerianEnum isEulerian() {
        return null;
    }

    public int nbOddDegree() {
        return -1;
    }

    public List<Pair<Node, Integer>> getDegreeForEachNode() {
        return null;
    }

    public List<Edge> computeChineseCircuit(PairWiseMatchingStrategy pairWiseMatchingStrategy) {
        return null;
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
