package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

import java.util.Arrays;

/**
 * Represents a cost matrix of values from mixing two alleles
 * This data structure is immutable. New values and alleles cannot be added.
 *
 * Home allele is always index 0.
 */
public class CostMatrix {

    private String[] _alleleNames;
    private int[][] _matrix;

    /**
     * A cost matrix is a basically a 2-D array of integers.
     * Rows and columns must be equal in size.
     *
     * Integer values should be greater than 0, but this is not enforced
     * currently.
     */
    public CostMatrix(int[][] costMatrix) {
        initCostMatrix(costMatrix);
        _alleleNames = null;
    }

    /**
     * Same as other constructor, but allows the addition of
     * named alleles.
     *
     * The number of names must match the rows of the matrix.
     */
    public CostMatrix(int[][] costMatrix,  String[] alleleNames) {
        if (alleleNames != null) {
            if (costMatrix.length != alleleNames.length) {
                throw new Errors.MatrixRowsNotEqualToNamedAlleles();
            }
            _alleleNames = Arrays.copyOf(alleleNames, alleleNames.length);
        }
        initCostMatrix(costMatrix);
    }

    /**
     * Sets cost matrix and makes sure the rows and columns are equal.
     * A copy is made of the matrix to ensure immutability.
     */
    private void initCostMatrix(int[][] costMatrix) {
        if (costMatrix.length != costMatrix[0].length) {
            throw new Errors.MatrixColumnsNotEqualToRows();
        }
        _matrix = new int[costMatrix.length][costMatrix.length];
        for (int i = 0; i < costMatrix.length; i++) {
            _matrix[i] = Arrays.copyOf(costMatrix[i], costMatrix[i].length);
        }
    }

    /**
     * Gets the cost of two allele pairs. First allele corresponds to rows,
     * and second allele corresponds to columns.
     *
     * An error is thrown is alleles are out of bounds of matrix.
     */
    public int getCost(int firstAllele, int secondAllele) {
        if (firstAllele < 0 || firstAllele >= _matrix.length || secondAllele < 0 || secondAllele >= _matrix.length) {
            throw new IndexOutOfBoundsException();
        }
        return _matrix[firstAllele][secondAllele];
    }

    /**
     * Gets the name of the allele.
     * If no allele name is provided, the index will be returned as the name of the allele.
     */
    public String getAlleleName(int alleleIndex)  {
        if (_alleleNames != null && (alleleIndex < 0 || alleleIndex >= _alleleNames.length)) {
            throw new IndexOutOfBoundsException();
        }

        if (_alleleNames != null) {
            return new String(_alleleNames[alleleIndex]);
        } else {
            return Integer.toString(alleleIndex);
        }
    }

    /**
     * Gets number of distinct alleles in matrix.
     */
    public int size() {
        return _matrix.length;
    }

    /**
     * Gets a copy of the array of alleles, excluding the allele 0.
     *
     * This helps avoid mistakenly generating an array with the 0 index included.
     */
    public int[] getAlleles() {
        int[] cities = new int[_matrix.length - 1];
        for (int i = 1; i < _matrix.length; i++) {
            cities[i - 1] = i;
        }
        return cities;
    }
}