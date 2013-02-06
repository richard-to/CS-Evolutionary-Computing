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
     * Builds a cost matrix with a certain size and price range.
     *
     * Not that minPrice and maxPrice are not checked.
     * @param size
     * @param minPrice
     * @param maxPrice
     * @return CostMatrix
     */
    public CostMatrix buildMatrix(int size, int minPrice, int maxPrice) {

        if (minPrice < 1 || maxPrice < 1) {
            throw new Errors.PriceLessThanZero();
        }

        if (minPrice > maxPrice) {
            throw new Errors.MinPriceGreaterThanMaxPrice();
        }

        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int g = 0; g < size; g++) {
                matrix[i][g] = _random.nextInt(maxPrice - minPrice + 1) + minPrice;
            }
        }
        return new CostMatrix(matrix);
    }

}
