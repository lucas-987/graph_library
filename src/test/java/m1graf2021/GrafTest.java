package m1graf2021;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import tools.ConstructorTools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrafTest {

    @Test
    public void testConstructorIntArray() {
        //Le principe :
        //tu appelle la méthode que tu veux tester dans des conditions où tu peux prédire le résultat
        //et tu vérifie que le résultat réel obtenue par la méthode correspond
        // ex : ici je crée un objet graf grace au constructeur et je vérifie que l'adjEdList du constructeur
        // correspond à ce que j'ai renseigné
        // il ne faut pas oublié de mettre @Test avant la méthode de test
        // on test qqch avec les méthodes Assert.assert (voir en dessous)
        Graf graf = new Graf(2,0,3,0,4,0);

        Map<Node, List<Edge>> adjEdList = graf.getAdjEdList();

        Node node2 = graf.getNode(2);
        Node node3 = graf.getNode(3);
        Node node4 = graf.getNode(4);

        List<Edge> childrenOfNode1 = adjEdList.get(graf.getNode(1));
        Assert.assertEquals(childrenOfNode1.size(), 1); // ici la méthode Assert.assertEquals
        Node childOf1 = childrenOfNode1.get(0).to();
        Assert.assertEquals(childOf1, node2);

        List<Edge> childrenOfNode2 = adjEdList.get(graf.getNode(2));
        Assert.assertEquals(childrenOfNode2.size(), 1);
        Node childOf2 = childrenOfNode2.get(0).to();
        Assert.assertEquals(childOf2, node3);

        List<Edge> childrenOfNode3 = adjEdList.get(graf.getNode(3));
        Assert.assertEquals(childrenOfNode3.size(), 1);
        Node childOf3 = childrenOfNode3.get(0).to();
        Assert.assertEquals(childOf3, node4);

        List<Edge> childrenOfNode4 = adjEdList.get(graf.getNode(4));
        Assert.assertEquals(childrenOfNode4.size(), 0);
    }

    @Test
    public void testConstructorAdjacencyMatrix() {
        int[][] grafAdjMatrix = {{0,2,0,1},
                                 {1,1,0,0},
                                 {0,0,1,3},
                                 {0,0,0,0}};

        Graf graf = (Graf) ConstructorTools.usePrivateConstructor(Graf.class, new Class[] {int[][].class}, new Object[] {grafAdjMatrix});

        if(graf == null) {
            Assert.fail();
        }

        Node node1 = graf.getNode(1);
        Node node2 = graf.getNode(2);
        Node node3 = graf.getNode(3);
        Node node4 = graf.getNode(4);

        Assert.assertNotEquals(null, node1);
        Assert.assertNotEquals(null, node2);
        Assert.assertNotEquals(null, node3);
        Assert.assertNotEquals(null, node4);

        Map<Node, List<Edge>> adjEdList = graf.getAdjEdList();

        int nbChild1To1InNode1 = 0;
        int nbChild1To2InNode1 = 0;
        int nbChild1To3InNode1 = 0;
        int nbChild1To4InNode1 = 0;

        int nbChild2To1InNode2 = 0;
        int nbChild2To2InNode2 = 0;
        int nbChild2To3InNode2 = 0;
        int nbChild2To4InNode2 = 0;

        int nbChild3To1InNode3 = 0;
        int nbChild3To2InNode3 = 0;
        int nbChild3To3InNode3 = 0;
        int nbChild3To4InNode3 = 0;

        int nbChild4To1InNode4 = 0;
        int nbChild4To2InNode4 = 0;
        int nbChild4To3InNode4 = 0;
        int nbChild4To4InNode4 = 0;

        for(Edge edge : adjEdList.get(node1)) {
            if(edge.from().equals(node1)) {
                if(edge.to().equals(node1))
                    nbChild1To1InNode1++;
                else if(edge.to().equals(node2))
                    nbChild1To2InNode1++;
                else if(edge.to().equals(node3))
                    nbChild1To3InNode1++;
                else if(edge.to().equals(node4))
                    nbChild1To4InNode1++;
            }
        }

        for(Edge edge : adjEdList.get(node2)) {
            if(edge.from().equals(node2)) {
                if(edge.to().equals(node1))
                    nbChild2To1InNode2++;
                else if(edge.to().equals(node2))
                    nbChild2To2InNode2++;
                else if(edge.to().equals(node3))
                    nbChild2To3InNode2++;
                else if(edge.to().equals(node4))
                    nbChild2To4InNode2++;
            }
        }

        for(Edge edge : adjEdList.get(node3)) {
            if(edge.from().equals(node3)) {
                if(edge.to().equals(node1))
                    nbChild3To1InNode3++;
                else if(edge.to().equals(node2))
                    nbChild3To2InNode3++;
                else if(edge.to().equals(node3))
                    nbChild3To3InNode3++;
                else if(edge.to().equals(node4))
                    nbChild3To4InNode3++;
            }
        }

        for(Edge edge : adjEdList.get(node4)) {
            if(edge.from().equals(node4)) {
                if(edge.to().equals(node1))
                    nbChild4To1InNode4++;
                else if(edge.to().equals(node2))
                    nbChild4To2InNode4++;
                else if(edge.to().equals(node3))
                    nbChild4To3InNode4++;
                else if(edge.to().equals(node4))
                    nbChild4To4InNode4++;
            }
        }

        Assert.assertEquals(0, nbChild1To1InNode1);
        Assert.assertEquals(2, nbChild1To2InNode1);
        Assert.assertEquals(0, nbChild1To3InNode1);
        Assert.assertEquals(1, nbChild1To4InNode1);

        Assert.assertEquals(1, nbChild2To1InNode2);
        Assert.assertEquals(1, nbChild2To2InNode2);
        Assert.assertEquals(0, nbChild2To3InNode2);
        Assert.assertEquals(0, nbChild2To4InNode2);

        Assert.assertEquals(0, nbChild3To1InNode3);
        Assert.assertEquals(0, nbChild3To2InNode3);
        Assert.assertEquals(1, nbChild3To3InNode3);
        Assert.assertEquals(3, nbChild3To4InNode3);

        Assert.assertEquals(0, nbChild4To1InNode4);
        Assert.assertEquals(0, nbChild4To2InNode4);
        Assert.assertEquals(0, nbChild4To3InNode4);
        Assert.assertEquals(0, nbChild4To4InNode4);
    }

    @Test
    public void testToAdjMatrix() {
        int[][] grafAdjMatrix = {{0,2,0,1},
                                 {1,1,0,0},
                                 {0,0,1,3},
                                 {0,0,0,0}};

        Graf graf = (Graf) ConstructorTools.usePrivateConstructor(Graf.class, new Class[] {int[][].class}, new Object[] {grafAdjMatrix});

        if(graf == null) {
            Assert.fail();
        }

        int[][] resultMatrix = graf.toAdjMatrix();

        boolean resultTrue = java.util.Arrays.deepEquals(grafAdjMatrix, resultMatrix);
        Assert.assertTrue(resultTrue);
    }

    @Test
    public void testToSuccessorArray() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);

        int[] successorArray = graf.toSuccessorArray();

        boolean successorArrayTrue = java.util.Arrays.equals(successorArray, new int[] {2,2,0,1,2,0,3,4,4,4,0,0});
        Assert.assertTrue(successorArrayTrue);
    }

    @Test
    public void testGetReverse() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);

        Graf actualReverseGraf = graf.getReverse();

        Graf expectedReverseGraf = new Graf(2,0,1,1,2,0,3,0,3,3,3,0);

        Assert.assertEquals(expectedReverseGraf, actualReverseGraf);
    }

    @Test
    public void testGetTransitiveClosure() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);

        Graf actualTransitiveClosure = graf.getTransitiveClosure();

        Graf expectedTransitiveClosure = new Graf(1,2,0,1,2,0,3,4,0,0);

        Assert.assertEquals(expectedTransitiveClosure, actualTransitiveClosure);
    }

    @Test
    public void testGetBFS() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);

        List<Node> actualBFS = graf.getBFS();

        List<Node> opened = graf.getAllNodes();
        List<Node> closed = new ArrayList<>();

        int nbNodes = graf.nbNodes();

        for(int i=1; i<nbNodes; i++) {
            List<Node> nodesInside = actualBFS.subList(0,i-1);
            List<Node> nodesOutside = actualBFS.subList(i,nbNodes-1);
            boolean borderEmpty = true;
            for(int j=0; j<nodesInside.size(); j++){
                Node node = nodesInside.get(j);

                int nbOfNeighborOutside = 0;
                boolean isOpen = opened.contains(node);

                for(Node neighbor : graf.getSuccessors(node)) {
                    if(!nodesInside.contains(neighbor)) {
                        borderEmpty = false;
                    }
                    if(isOpen && nodesOutside.contains(node))
                        nbOfNeighborOutside++;
                }

                if(isOpen && nbOfNeighborOutside > 0) {
                    opened.remove(node);
                    closed.add(node);
                }
            }

            if(borderEmpty) Assert.fail();

            Node firstOutsideNode = actualBFS.get(i);
            for(int j=0; j<nodesInside.size(); j++) {
                Node node = nodesInside.get(j);
                if(opened.contains(node)) {
                    if(graf.getSuccessors(node).contains(firstOutsideNode)) {
                        break;
                    }
                    else {
                        Assert.fail();
                    }
                }
            }
        }

        Assert.assertTrue(true);
    }

    @Test
    public void testGetDFS() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);
        List<Node> actualDFS = graf.getDFS();
        System.out.println("debug break point");
    }

    @Test
    public void testToDotString() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);
        String actualDotString = graf.toDotString();
        System.out.println("breakpoint");
    }

    @Test
    public void testToDotFile() {
        Graf graf = new Graf(2,2,0,1,2,0,3,4,4,4,0,0);
        graf.toDotFile("/media/lucas/Data/Users/User/Documents/cours/master/m1/graphes/dotFile");
    }

    @Test
    public void testFromDotString() {
        String dotString = "digraph test {\n" +
                            "     1 -> 6;\n" +
                            "     3 -> 1, 7;\n" +
                            "     4 -> 3 [len=8]\n" +
                            "     3 -> 8 [label=14]\n" +
                            "     8 -> 2 [label=-8, len=-8]\n";

        Graf actualGraf = Graf.fromDotString(dotString);
        Graf expectedGraf = new Graf(6,0,0,1,7,8,0,3,0,0,0,2,0);
    }

    @Test
    public void testFromDotFile() {
        Graf actualGraf = Graf.fromDotFile("/media/lucas/Data/Users/User/Documents/cours/master/m1/graphes/dotFileGenerated.gv");
        System.out.println("breakpoint");
    }

    @Test
    public void testEquals() {
        Graf graf1 = new Graf(2,2,3,0,1,0,2,0);
        Graf copyGraf1 = new Graf(2,2,3,0,1,0,2,0);

        Graf graf2 = new Graf(2,3,3,0,1,0,2,0);

        Assert.assertFalse(graf1.equals(graf2));
        Assert.assertTrue(graf1.equals(copyGraf1));
    }
}
