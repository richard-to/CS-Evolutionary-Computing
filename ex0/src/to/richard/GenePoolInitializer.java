package to.richard;

import java.util.Random;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

/**
 * GenePoolInitializer helps initializes the first generation of genotypes.
 */
public class GenePoolInitializer {

    private int _maxValue;
    private int _populationSize;
    private Random _random;

    public GenePoolInitializer(int maxValue, int populationSize, Random random) {
        this._maxValue = maxValue;
        this._populationSize = populationSize;
        this._random = random;
    }

    /**
     * Initializes a GenePool for the first generation.
     *
     * @return GenePool
     */
    public GenePool initializeGenePool() {
        GenePool genePool = new GenePool(_maxValue, _populationSize);
        for (int i = 0; i < _populationSize; i++) {
            genePool.addGenotype(createRandomGenotype());
        }
        return genePool;
    }

    /**
     * Creates a random Genotype.
     *
     * @return Genotype
     */
    public Genotype createRandomGenotype() {
        int value = _random.nextInt(_maxValue);
        int bitArrayLength = Integer.toBinaryString(_maxValue - 1).length();
        return new Genotype(value, bitArrayLength);
    }
}
