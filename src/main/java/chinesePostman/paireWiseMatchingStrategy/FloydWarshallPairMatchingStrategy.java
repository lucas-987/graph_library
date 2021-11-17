package chinesePostman.paireWiseMatchingStrategy;

import chinesePostman.Pair;
import m1graf2021.Edge;
import m1graf2021.Node;
import m1graf2021.UndirectedGraf;

import java.util.List;
import java.util.Map;

public class FloydWarshallPairMatchingStrategy implements PairWiseMatchingStrategy {
    @Override
    public List<Edge> getEdgesToDuplicate(UndirectedGraf graph) {
        return null;
    }

    public Map<Pair<Node, Node>, Pair<Integer, Node>> floydWarshall(UndirectedGraf graph) {
        return null;
    }
}
