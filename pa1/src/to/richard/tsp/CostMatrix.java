package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

/**
 * Represents a cost matrix of flights from one city to another.
 * This data structure is immutable. New prices and cities cannot be added.
 *
 * Home city is always index 0.
 */
public class CostMatrix {

    private String[] _cityNames;
    private int[][] _matrix;

    /**
     * Constructor to initialize cost matrix.
     *
     * @param costMatrix
     */
    public CostMatrix(int[][] costMatrix) {
        initCostMatrix(costMatrix);
        _cityNames = null;
    }

    /**
     * Constructor to initialize cost matrix with city names corresponding to index.
     *
     * Using city names is optional. If no city names are provided, then the index will
     * be used as the city name.
     *
     * An exception is thrown if the number of city names does not match the cost matrix size.
     *
     * If cityNames is null, then no exception will be thrown. Instead cityNames will be null and
     * revert to using the index as stated.
     *
     * The cost matrix must also have the same rows and columns.
     *
     * @param costMatrix
     * @param cityNames
     */
    public CostMatrix(int[][] costMatrix,  String[] cityNames) {
        if (cityNames != null) {
            if (costMatrix.length != cityNames.length) {
                throw new Errors.MatrixRowsNotEqualToCityNames();
            }
            _cityNames = cityNames.clone();
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
        _matrix = costMatrix.clone();
    }

    /**
     * Gets the cost of a trip from one place to another using cost matrix.
     *
     * Allows from and to to be the same city. Doesn't make sense, but is allowed here.
     *
     * @param from
     * @param to
     * @return
     */
    public int getCost(int from, int to) {
        if (from < 0 || from >= _matrix.length || to < 0 || to >= _matrix.length) {
            throw new IndexOutOfBoundsException();
        }
        return _matrix[from][to];
    }

    /**
     * Gets the name of the city.
     * If no city name is provided, the index will be returned as the name of the city.
     * @param cityIndex
     * @return Name of city
     */
    public String getCityName(int cityIndex)  {
        if (_cityNames != null && (cityIndex < 0 || cityIndex >= _cityNames.length)) {
            throw new IndexOutOfBoundsException();
        }

        if (_cityNames != null) {
            return new String(_cityNames[cityIndex]);
        } else {
            return Integer.toString(cityIndex);
        }
    }

    /**
     * Gets number of destination cities in matrix.
     * @return
     */
    public int size() {
        return _matrix.length;
    }
}