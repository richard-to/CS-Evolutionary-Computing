package to.richard;

import java.util.Map;
import java.util.TreeMap;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Randomly distributes values based on probabilities
 * @param <E>
 */
public class ProbabilityDistribution<E> {

    public static double MAX_PROBABILITY = 1.0;

    private TreeMap<Double, E> _distributionMap;
    private double _totalProbabilty;
    private IRandom _random;

    public ProbabilityDistribution(IRandom random) {
        _distributionMap = new TreeMap<Double, E>();
        _totalProbabilty = 0.0;
        _random = random;
    }

    /**
     * Adds distribution ceiling point and value pair
     *
     * If the probability exceeds 1.0, then the probability will not be added.
     * This takes into account previous probabilities added to distribution.
     *
     * @param probability The probability of selecting the value.
     * @param value
     * @return Distribution<E>
     */
    public ProbabilityDistribution<E> add(double probability, E value) {
        if (probability <= MAX_PROBABILITY &&
                probability + _totalProbabilty <= MAX_PROBABILITY) {
            _totalProbabilty += probability;
            _distributionMap.put(_totalProbabilty, value);
        }
        return this;
    }

    /**
     * Gets value within a distribution point
     * @param point
     * @return E
     */
    public E get(double point){
        Map.Entry<Double, E> entry = _distributionMap.higherEntry(point);
        if(entry != null){
            return entry.getValue();
        }
        return null;
    }

    /**
     * Picks a random element from distribution.
     *
     * @return E
     */
    public E getRandom() {
        double probability = _random.nextDouble();
        return get(probability);
    }
}
