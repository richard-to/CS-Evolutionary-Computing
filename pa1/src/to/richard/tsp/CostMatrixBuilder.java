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

    public CostMatrixBuilder(IRandom random) {
        _random = random;
    }

    /**
     * Builds a cost matrix with a certain size and value range. Also includes alleleNames.
     *
     * @param size
     * @param minValue
     * @param maxValue
     * @param allelNames
     * @return CostMatrix
     */
    public CostMatrix buildMatrix(int size, int minValue, int maxValue, String[] allelNames) {

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
        return new CostMatrix(matrix, allelNames);
    }

    /**
     * Builds a cost matrix with a certain size and value range.
     * @param size
     * @param minValue
     * @param maxValue
     * @return CostMatrix
     */
    public CostMatrix buildMatrix(int size, int minValue, int maxValue) {
        return buildMatrix(size, minValue, maxValue, null);
    }

}
