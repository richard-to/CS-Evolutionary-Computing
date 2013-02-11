package to.richard.tsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

/**
 * Used for testing, so we don't have to deal with random sampling.
 * @param <E>
 */
public class MockSampler<E> implements ISampler<E> {

    /**
     * Returns the first element in the list.
     */
    @Override
    public E sampleOne(List<Pair<Double, E>> valueMap) {
        return valueMap.get(0).getSecondValue();
    }

    /**
     * Just returns the first n elements in list.
     */
    @Override
    public ArrayList<E> sample(List<Pair<Double, E>> valueMap, int n) {
        ArrayList<E> samples = new ArrayList<E>();
        for (int i = 0; i < n; i++) {
            samples.add(valueMap.get(i).getSecondValue());
        }
        return samples;
    }
}
