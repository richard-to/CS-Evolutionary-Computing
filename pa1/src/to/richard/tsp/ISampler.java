package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for sampling from gene pool
 */
public interface ISampler<E> {
    public E sampleOne(List<Pair<Double, E>> valueMap);
    public ArrayList<E> sample(List<Pair<Double, E>> valueMap, int n);
}
