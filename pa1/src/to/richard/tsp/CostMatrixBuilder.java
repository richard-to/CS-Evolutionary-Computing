package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Factory for building a CostMatrix.
 */
public class CostMatrixBuilder {

    private IRandom _random;

    public CostMatrixBuilder() {
        _random = new Random();
    }

    /**
     * IRandom is used to randomly generate costs for row/column pairs.
     */
    public CostMatrixBuilder(IRandom random) {
        _random = random;
    }

    /**
     * Builds a cost matrix with a certain size and value range. Also includes alleleNames to
     * correspond to indexes if desired.
     *
     * Size obviously cannot be less than 0. Size will be used for rows and columns of matrix.
     * Size is the number of alleles for the problem.
     *
     * Min and max values cannot be less than 0.
     *
     * The number of allele names must batch the size.
     */
    public CostMatrix buildMatrix(int size, int minValue, int maxValue, String[] alleleNames) {

        if (minValue < 1 || maxValue < 1) {
            throw new Errors.ValueLessThanZero();
        }

        if (minValue > maxValue) {
            throw new Errors.MinValueGreaterThanMaxValue();
        }

        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int g = 0; g < size; g++) {
                matrix[i][g] = _random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }
        return new CostMatrix(matrix, alleleNames);
    }

    /**
     * Builds a cost matrix without corresponding allele names.
     */
    public CostMatrix buildMatrix(int size, int minValue, int maxValue) {
        return buildMatrix(size, minValue, maxValue, null);
    }

}
