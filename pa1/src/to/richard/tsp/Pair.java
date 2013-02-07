package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Represents a pair of related objects.
 *
 * @param <V1>
 * @param <V2>
 */
public class Pair<V1, V2> {
    private V1 _firstValue;
    private V2 _secondValue;

    public Pair(V1 firstValue, V2 secondValue) {
        _firstValue = firstValue;
        _secondValue = secondValue;
    }

    public V1 getFirstValue() {
        return _firstValue;
    };

    public V2 getSecondValue() {
        return _secondValue;
    }
}
