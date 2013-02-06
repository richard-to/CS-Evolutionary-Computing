package to.richard.tsp;

import sun.net.idn.StringPrep;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

/**
 * Represents a cost matrix of flights from one destination to another.
 */
public class CostMatrix {

    String[] _cityNames;
    int[][] _matrix;

    /**
     * Constructor to initialize cost matrix.
     *
     * @param costMatrix
     * @throws Exception
     */
    public CostMatrix(int[][] costMatrix) throws Exception {
        initCostMatrix(costMatrix);
    }

    /**
     * Constructor to initialize cost matrix with city names corresponding to index.
     *
     * Using city names is optional. If no city names are provided, then the index will
     * be used as the city name.
     *
     * An exception is thrown if the number of city names does not match the cost matrix size.
     *
     * The cost matrix must also have the same rows and columns.
     *
     * @param costMatrix
     * @param cityNames
     * @throws Exception
     */
    public void CostMatrix(int[][] costMatrix,  String[] cityNames) throws Exception {

        if (costMatrix.length != cityNames.length) {
            throw new Exception("City names must equal the number of cities in CostMatrix");
        }
        _cityNames = cityNames;
        initCostMatrix(costMatrix);
    }

    /**
     * Sets cost matrix and makes sure the rows and columns are equal.
     * @param costMatrix
     */
    private void initCostMatrix(int[][] costMatrix) throws Exception {
        if (costMatrix.length != costMatrix[0].length) {
            throw new Exception("Number of rows must equal the number of columns.");
        }
        _matrix = costMatrix;
    }

    /**
     * Gets the cost of a trip from one place to another using cost matrix.
     *
     * @param from
     * @param to
     * @return
     * @throws IndexOutOfBoundsException
     */
    public int getCost(int from, int to) throws IndexOutOfBoundsException {
        if (from < 0 || from >= _matrix.length || to < 0 || to >= _matrix.length) {
            throw new IndexOutOfBoundsException();
        }
        return _matrix[from][to];
    }
}