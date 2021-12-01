package chinesePostman.PairWiseMatchingStrategy;
import chinesePostman.paireWiseMatchingStrategy.FloydWarshallPairMatchingStrategy;
import m1graf2021.Graf;
import m1graf2021.UndirectedGraf;
import org.junit.Assert;
import org.junit.Test;
public class FloydWarshallPaireMatchingStrategyTest {


    @Test
    public void FloydWarshallPairMatchingStrategytest(){
        String dotString = "graph test {\n" +
                "     1 -- 6;\n" +
                "     3 -- 1, 7;\n" +
                "     4 -- 3 [len=8]\n" +
                "     3 -- 8 [label=14]\n" +
                "     8 --  2 [label=-8, len=-8]\n";
        String dotString1 = "graph test{\n"+
                "     1 -- 3 [len=-2]\n"+
                "     2 -- 1 [len=4]\n"+
                "     2 -- 3 [len=3]\n"+
                "     3 -- 4 [len=2]\n"+
                "     4 -- 2 [len=-1]\n";
        UndirectedGraf grf1 = (UndirectedGraf) Graf.fromDotString(dotString1);
        System.out.println("");
        FloydWarshallPairMatchingStrategy floydWarshallPairMatchingStrategy = new FloydWarshallPairMatchingStrategy();
        floydWarshallPairMatchingStrategy.floydWarshall(grf1);

    }


}
