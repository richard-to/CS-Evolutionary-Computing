package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import to.richard.tsp.CostMatrixBuilder;
import to.richard.tsp.CostMatrix;
import to.richard.tsp.Errors;
import to.richard.tsp.MockRandom;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Richard To
 * Date: 2/6/13
 */
public class CostMatrixBuilderTest {

    @Test
    public void testBuildMatrix() throws Exception {
        int size = 2;
        int minPrice = 10;
        int maxPrice = 30;
        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{30, 50, 70, 60}));
        MockRandom random = new MockRandom();
        random.setIntegerSequence(sequence);

        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(size, minPrice, maxPrice);

        assertEquals(60, costMatrix.getCost(0, 1));
        assertEquals(80, costMatrix.getCost(1, 0));
    }

    @Test(expected = Errors.MinPriceGreaterThanMaxPrice.class)
    public void testMinPriceGreaterThan() throws Exception {
        MockRandom random = new MockRandom();
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(2, 40, 20);
    }

    @Test(expected = Errors.PriceLessThanZero.class)
    public void testPriceLessThanZeroMin() throws Exception {
        MockRandom random = new MockRandom();
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(2, -20, 20);
    }

    @Test(expected = Errors.PriceLessThanZero.class)
    public void testPriceLessThanZeroMax() throws Exception {
        MockRandom random = new MockRandom();
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(2, 20, -20);
    }
}
