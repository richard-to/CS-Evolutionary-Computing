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
     * Constructor to initialize cost matrix.
     *
     * @param costMatrix
     */
    public CostMatrix(int[][] costMatrix) {
        initCostMatrix(costMatrix);
        _alleleNames = null;
    }

    /**
     * Constructor to initialize cost matrix with named alleles  corresponding to index.
     *
     * Using named alleles is optional. If no named allele are provided, then the index will
     * be used as the allele name.
     *
     * An exception is thrown if the number of alleles names does not match the cost matrix size.
     *
     * If alleleNames is null, then no exception will be thrown. Instead alleleNames will be null and
     * revert to using the index as stated.
     *
     * The cost matrix must also have the same rows and columns.
     *
     * @param costMatrix
     * @param alleleNames
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
     * @param costMatrix
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
     * Gets the cost of a mixing two alleles.
     *
     * @param firstAllele
     * @param secondAllele
     * @return
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
     * @param alleleIndex
     * @return Name of allele
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
     * @return
     */
    public int size() {
        return _matrix.length;
    }

    /**
     * Gets an array of alleles.
     *
     * This returns an array of alleles from one to the number of alleles.
     *
     * This basically just excludes the home allele and helps avoid mistakenly generating
     * an array with the 0 index.
     * @return
     */
    public int[] getAlleles() {
        int[] cities = new int[_matrix.length - 1];
        for (int i = 1; i < _matrix.length; i++) {
            cities[i - 1] = i;
        }
        return cities;
    }
}