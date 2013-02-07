package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

import java.util.ArrayList;
import java.util.Map;

/**
 * Interface for sampling from gene pool
 */
public interface ISampler<E> {
    public E sampleOne(Map<Integer, E> valueMap);
    public ArrayList<E> sample(Map<Integer, E> valueMap, int n);
}
