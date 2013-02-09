package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import to.richard.tsp.*;

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

        assertEquals(60, costMatrix.getCost(new Allele(0), new Allele(1)), 0.1);
        assertEquals(80, costMatrix.getCost(new Allele(1), new Allele(0)), 0.1);
    }

    @Test
    public void testBuildMatrixWithCityNames() throws Exception {
        int size = 2;
        int minPrice = 10;
        int maxPrice = 30;
        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{30, 50, 70, 60}));
        MockRandom random = new MockRandom();
        random.setIntegerSequence(sequence);

        String[] cityNames = {"City0", "City1"};
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(size, minPrice, maxPrice, cityNames);

        assertEquals(60, costMatrix.getCost(new Allele(0), new Allele(1)), 0.1);
        assertEquals(80, costMatrix.getCost(new Allele(1), new Allele(0)), 0.1);
        assertEquals("City0", costMatrix.getAlleleName(new Allele(0)));
    }

    @Test(expected = Errors.MinValueGreaterThanMaxValue.class)
    public void testMinPriceGreaterThan() throws Exception {
        MockRandom random = new MockRandom();
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(2, 40, 20);
    }

    @Test(expected = Errors.ValueLessThanZero.class)
    public void testPriceLessThanZeroMin() throws Exception {
        MockRandom random = new MockRandom();
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(2, -20, 20);
    }

    @Test(expected = Errors.ValueLessThanZero.class)
    public void testPriceLessThanZeroMax() throws Exception {
        MockRandom random = new MockRandom();
        CostMatrixBuilder costMatrixBuilder = new CostMatrixBuilder(random);
        CostMatrix costMatrix = costMatrixBuilder.buildMatrix(2, 20, -20);
    }
}
