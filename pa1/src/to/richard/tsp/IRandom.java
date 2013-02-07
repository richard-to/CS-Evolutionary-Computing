package to.richard.tsp;

import java.util.Random;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Simplified interface for generating random numbers.
 *
 * This interface implements what is needed for my SGA.
 * This interface is needed to allow mock random objects for testing.
 */
public interface IRandom {

    /**
     * When using classes from Java library, we cannot pass in
     * IRandom, so we need to use the Java Random class.
     * @return Random
     */
    public Random getJavaRandom();

    /**
     * Returns the next pseudorandom, uniformly distributed int value from this random number generator's sequence.
     * @param n
     * @return
     */
    public int nextInt(int n);

    /**
     * Returns the next pseudorandom, uniformly distributed double value between 0.0 and 1.0 from this random number generator's sequence.
     * @return
     */
    public double nextDouble();
}
