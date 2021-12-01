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

        final int INF = 99999, V = graph.nbNodes();
        int dist[][] = new int[V][V];
        int i, j, k;

        int g[][] = graph.toAdjMatrix().clone();
        int w = 0;
        Edge edge = graph.getEdge( new Node(1), new Node(1));
        w = edge.getWeight();


        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                dist[i][j] = g[i][j];
/*                Edge edge = graph.getEdge( new Node(i), new Node(j));
                w = edge.getWeight();*/
                /*dist[i][j] = edge.getWeight();;*/

            }
        }


        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j] /*&& dist[i][k] != INF && dist[k][j] != INF*/) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }


        return null;
    }
}
