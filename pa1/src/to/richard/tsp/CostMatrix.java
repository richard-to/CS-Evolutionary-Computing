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
     * Gets the cost of two allele value pairs. First allele corresponds to rows,
     * and second allele corresponds to columns.
     *
     * An error is thrown is alleles are out of bounds of matrix.
     */
    public double getCost(Allele firstAllele, Allele secondAllele) {
        return _matrix[firstAllele.getValue()][secondAllele.getValue()];
    }

    /**
     * Gets the name of the allele.
     * If no allele name is provided, the allele value will be returned as
     * the name of the allele.
     */
    public String getAlleleName(Allele allele)  {
        if (_alleleNames != null) {
            return new String(_alleleNames[allele.getValue()]);
        } else {
            return Integer.toString(allele.getValue());
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
    public Allele[] getAlleles() {
        Allele[] alleles = new Allele[_matrix.length - 1];
        for (int i = 1; i < _matrix.length; i++) {
            alleles[i - 1] = new Allele(i);
        }
        return alleles;
    }
}