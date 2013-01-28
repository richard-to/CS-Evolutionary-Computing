package to.richard;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

import java.util.Random;

/**
 * Represents the pool of genes for the current generation.
 */
public class XSquaredGenePool {

    /**
     * Creates a random XSquaredGenotype.
     *
     * The bit length be adjusted to to fit the max value minus 1. This is
     * because the max value is exclusive
     * The minimum value will always be 0. This value is inclusive.
     *
     * @param maxValue
     * @return XSquaredGenotype
     */
    public static XSquaredGenotype createRandomGenotype(int maxValue) {
        Random rand = new Random();
        return XSquaredGenePool.createRandomGenotype(maxValue, rand);
    }

    /**
     * Creates a random XSquaredGenotype using your own random object.
     *
     * This is useful for using a specific seed for testing.
     *
     * @param maxValue
     * @param rand
     * @return
     */
    public static XSquaredGenotype createRandomGenotype(int maxValue, Random rand) {
        int value = rand.nextInt(maxValue);
        int bitArrayLength = Integer.toBinaryString(maxValue).length();
        return new XSquaredGenotype(value, bitArrayLength);
    }

    private Random _rand;
    private int _maxValue;
    private int _populationSize;
    private XSquaredGenotype[] _genotypes;

    /**
     * Constructor for gene pool with specified random number generation class.
     *
     * @param maxValue
     * @param populationSize
     * @param rand
     */
    public XSquaredGenePool(int maxValue, int populationSize, Random rand) {
        _maxValue = maxValue;
        _populationSize = populationSize;
        _genotypes = new XSquaredGenotype[_populationSize];
        _rand = rand;
        for (int i = 0; i < _populationSize; i++) {
            _genotypes[i] = XSquaredGenePool.createRandomGenotype(_maxValue, _rand);
        }
    }
}
