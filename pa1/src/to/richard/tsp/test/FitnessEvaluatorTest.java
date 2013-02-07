package to.richard.tsp.test;

import org.junit.Test;
import to.richard.tsp.CostMatrix;
import to.richard.tsp.FitnessEvaluator;
import to.richard.tsp.Genotype;

import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 2/6/13
 */
public class FitnessEvaluatorTest {

    @Test
    public void testEvaluate() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 23}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new int[]{1, 2});
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        // 0,1 - 1,2 - 2,0
        // 30  + 23 + 42
        int expectedFitness = 95;
        assertEquals(expectedFitness, fitnessEvaluator.evaluate(genotype));
    }
}
