package to.richard.tsp;

import java.util.Random;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Simplified interface for generating random numbers.
 *
 * This interface is needed to allow mock random objects for testing.
 * A fixed seed could be used for testing, but is not quite as convenient.
 */
public interface IRandom {

    /**
     * When using classes from Java library, we cannot pass in
     * IRandom, so we need to be able to get an instance of the Java Random class.
     */
    public Random getJavaRandom();

    /**
     * Returns the next pseudo-random, uniformly distributed int value from this random number generator's sequence.
     */
    public int nextInt(int n);

    /**
     * Returns the next pseudo-random, uniformly distributed double value between 0.0 and 1.0 from this random number generator's sequence.
     */
    public double nextDouble();
}
