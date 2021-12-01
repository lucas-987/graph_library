package chinesePostman.paireWiseMatchingStrategy;

import chinesePostman.Pair;
import m1graf2021.Edge;
import m1graf2021.Node;
import m1graf2021.UndirectedGraf;

import java.util.List;

public interface PairWiseMatchingStrategy {
    public List<Edge> getEdgesToDuplicate(UndirectedGraf graph);
}
