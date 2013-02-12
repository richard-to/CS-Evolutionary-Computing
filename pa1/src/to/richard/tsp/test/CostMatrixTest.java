package to.richard.tsp.test;

import org.junit.Test;

import to.richard.tsp.Allele;
import to.richard.tsp.CostMatrix;
import to.richard.tsp.Errors;

import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 2/6/13
 */
public class CostMatrixTest {

    @Test
    public void testGetCostAndCityNameAndSize() throws Exception {
        int[][] matrix = {{10, 20}, {30, 40}};
        String[] cityNames = {"City0", "City1"};
        CostMatrix costMatrix = new CostMatrix(matrix, cityNames);

        double cost = costMatrix.getCost(new Allele(0), new Allele(1));
        cost = 0;
        assertEquals(20, costMatrix.getCost(new Allele(0), new Allele(1)), 0.1);

        String city1 = costMatrix.getAlleleName(new Allele(1));
        city1 = "City2";
        assertEquals("City1", costMatrix.getAlleleName(new Allele(1)));

        assertEquals(2, costMatrix.size());
    }

    @Test
    public void testGetCityNameIndex() throws Exception {
        int[][] matrix = {{10, 20}, {30, 40}};
        CostMatrix costMatrix = new CostMatrix(matrix);
        assertEquals(20, costMatrix.getCost(new Allele(0), new Allele(1)), 0.1);
        assertEquals("1", costMatrix.getAlleleName(new Allele(1)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCityNameIndexBounds() throws Exception {
        int[][] matrix = {{10, 20}, {30, 40}};
        String[] cityNames = {"City0", "City1"};
        CostMatrix costMatrix = new CostMatrix(matrix, cityNames);
        costMatrix.getAlleleName(new Allele(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBounds() throws Exception {
        int[][] matrix = {{10, 20}, {30, 40}};
        String[] cityNames = {"City0", "City1"};
        CostMatrix costMatrix = new CostMatrix(matrix, cityNames);
        costMatrix.getCost(new Allele(4), new Allele(2));
    }

    @Test(expected = Errors.MatrixRowsGreaterThanEqualToNamedAlleles.class)
    public void testCityNamesNotEqualToMatrix() throws Exception {
        int[][] matrix = {{10, 20}, {30, 40}};
        String[] cityNames = {"City0"};
        CostMatrix costMatrix = new CostMatrix(matrix, cityNames);
    }
}
