package to.richard;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Represents a pool of genes for the current generation.
 *
 * The gene pool does not contain duplicates genotypes.
 */
public class GenePool {

    private int _maxValue;
    private int _populationSize;
    private LinkedHashSet<Genotype> _genotypeSet;
    private ArrayList<Genotype> _genotypeList;

    /**
     * Constructor for gene pool with specified random number generation class.
     *
     * This will create the initial generation.
     *
     * @param maxValue
     * @param populationSize
     */
    public GenePool(int maxValue, int populationSize) {
        _maxValue = maxValue;
        _populationSize = populationSize;
        _genotypeSet = new LinkedHashSet<Genotype>();
        _genotypeList = new ArrayList<Genotype>();
    }

    /**
     * Adds genotype to gene pool.
     *
     * If the genotype has a value greater than max or if the pool has reached
     * the population size, then the genotype will not be added to the pool.
     *
     * @param genotype
     * @return GenePool
     */
    public GenePool addGenotype(Genotype genotype) {
        if (genotype.getValue() < _maxValue &&
                _genotypeSet.size() < _populationSize &&
                _genotypeSet.add(genotype)) {
            _genotypeList.add(genotype);
        }
        return this;
    }

    /**
     * Gets a genotype at the specified index.
     *
     * @param index
     * @return Genotype
     */
    public Genotype getGenotype(int index) {
        Genotype genotype = _genotypeList.get(index);
        return genotype.clone();
    }

    public int getMaxValue() {
        return _maxValue;
    }

    public int getPopulationSize() {
        return _populationSize;
    }
}