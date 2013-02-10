package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of Stochastic Universal Sampling (SUS) algorithm.
 *
 * The SUS class inherits from the Roulette wheel. Building the roulette wheel
 * and sampleOne will be the same implementations. SUS just needs to override
 * the sample method.
 *
 * 1. Put elements on roulette wheel proportionately.
 * 2. Pick a random number between 0 and 1/n (n = number of elements)
 * 3. Sample element that is landed on. Now add 1/n to the random value.
 * 4. Sample that element and repeat until total samples is reached.
 */
public class StochasticUniversalSampler<E> extends RouletteWheel<E> {

    /**
     * Constructs a roulette wheel to randomly select objects, based on their weight.
     */
    public StochasticUniversalSampler(IRandom random) {
        _random = random;
    }

    @Override
    public ArrayList<E> sample(List<Pair<Double, E>> valueMap, int n) {
        TreeMap<Double, E> wheel = buildRouletteWheel(valueMap);
        ArrayList<E> sampledObjects = new ArrayList<E>();
        Map.Entry<Double, E> entry;
        double sampleStep = 1.0 / n;
        double randomStep = sampleStep * _random.nextDouble();
        for (int i = 0; i < n; i++) {
            entry = wheel.higherEntry(randomStep);
            sampledObjects.add(entry.getValue());
            randomStep += sampleStep;
        }
        return sampledObjects;
    }
}
