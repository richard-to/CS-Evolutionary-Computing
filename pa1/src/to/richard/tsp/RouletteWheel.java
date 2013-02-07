package to.richard.tsp;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Roulette Wheel algorithm implementation.
 *
 * Roulette Wheel is Immutable.
 *
 * Windowing, sigma scaling, and ranking can be
 * applied before adding points.
 *
 * The valueMap parameter is a Map. The key is an integer representing the weight of the
 * object. The higher the weight in proportion to the objects, the bigger the
 * roulette wheel slice. The value of the Map is any object.
 *
 * Pass in a LinkedHashMap to valueMap if insertion order is important. Seems only necessary
 * for testing.
 *
 * @param <E>
 */
public class RouletteWheel<E> implements ISampler<E> {

    private IRandom _random;

    /**
     * Constructs a roulette wheel to randomly select objects, based on their weight.
     *
     * @param random
     */
    public RouletteWheel(IRandom random) {
        _random = random;
    }

    /**
     * Picks one random element from distribution.
     *
     * Object is returned by reference.
     *
     * @return E
     */
    public E sampleOne(Map<Integer, E> valueMap) {
        TreeMap<Double, E> wheel = buildRouletteWheel(valueMap);
        double probability = _random.nextDouble();
        Map.Entry<Double, E> entry = wheel.higherEntry(probability);
        return entry.getValue();
    }

    /**
     * Samples n elements from roulette wheel.
     *
     * Object is returned by reference.
     *
     * @param n
     * @return E
     */
    public ArrayList<E> sample(Map<Integer, E> valueMap, int n) {
        TreeMap<Double, E> wheel = buildRouletteWheel(valueMap);
        ArrayList<E> sampledObjects = new ArrayList<E>();
        Map.Entry<Double, E> entry;
        double probability;
        for (int i = 0; i < n; i++) {
            probability = _random.nextDouble();
            entry = wheel.higherEntry(probability);
            sampledObjects.add(entry.getValue());
        }
        return sampledObjects;
    }

    public TreeMap<Double, E> buildRouletteWheel(Map<Integer, E> valueMap) {
        TreeMap<Double, E> wheel = new TreeMap<Double, E>();
        double totalProbability = 0.0;
        double probability = 0.0;
        double totalWeight = 0;

        for (Integer weight : valueMap.keySet()) {
            totalWeight += weight;
        }
        for (Map.Entry<Integer, E> entry : valueMap.entrySet()) {
            probability = entry.getKey() / totalWeight;
            totalProbability += probability;
            wheel.put(totalProbability, entry.getValue());
        }
        return wheel;
    }
}