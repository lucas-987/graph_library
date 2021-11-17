package chinesePostman.paireWiseMatchingStrategy;

import m1graf2021.Edge;
import m1graf2021.UndirectedGraf;

import java.util.List;

public interface PairWiseMatchingStrategy {
    public List<Edge> getEdgesToDuplicate(UndirectedGraf graph);
}
