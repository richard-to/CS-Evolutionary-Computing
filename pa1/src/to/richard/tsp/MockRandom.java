package to.richard.tsp;

import java.util.ArrayList;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Mocks random number generation with a known sequence. Ideal for unit testing.
 */
public class MockRandom implements IRandom {

    ArrayList<Double> _doubleSequence;
    ArrayList<Integer> _integerSequence;
    private java.util.Random _random;

    /**
     * Default constructor can be used if you are not testing the
     * against a class that requires java.util.Random.
     */
    public MockRandom() {
        _random = new java.util.Random();
    }

    /**
     * Construct a random number generator with specific seed.
     * This is necessary for when we need to use the Random
     * class in the Java library.
     */
    public MockRandom(long seed) {
        _random = new java.util.Random(seed);
    }

    /**
     * If using getDouble, make sure to set the sequence of doubles.
     */
    public MockRandom setDoubleSequence(ArrayList<Double> doubleSequence) {
        this._doubleSequence = doubleSequence;
        return this;
    }

    /**
     * If using getInt(int n), make sure to set the sequence of integers first.
     */
    public MockRandom setIntegerSequence(ArrayList<Integer> integerSequence) {
        this._integerSequence = integerSequence;
        return this;
    }

    @Override
    /**
     * Make sure to set a list of integer with at least the amount of numbers
     * needed for your test.
     *
     * Additionally, the n parameter is ignored. Make sure your numbers make sense.
     *
     * The sequence pops numbers of list in order.
     */
    public int nextInt(int n) {
        return this._integerSequence.remove(0);
    }

    @Override
    /**
     * Make sure to set a list of doubles with at least the amount of numbers
     * needed for your test.
     *
     * The sequence pops numbers of list in order.
     */
    public double nextDouble() {
        return this._doubleSequence.remove(0);
    }

    public java.util.Random getJavaRandom() {
        return _random;
    }
}
