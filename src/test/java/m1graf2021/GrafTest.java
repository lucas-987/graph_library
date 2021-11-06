package m1graf2021;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

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
}
