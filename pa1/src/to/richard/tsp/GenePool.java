package to.richard.tsp;

import java.util.HashSet;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Represents a pool of genes for the current generation.
 * Note that duplicates are allowed in GenePool. This means that
 * during initialization for generation 0, there could be a chance
 * that the same gene is generated. Not sure if this is the way to
 * go or not.
 */
public class GenePool {

    private List<Genotype> _genotypes;

    /**
     * GenePool constructor
     * @param genotypes
     */
    public GenePool(List<Genotype> genotypes) {
        _genotypes = genotypes;
    }

    /**
     * Gets a genotype at the specified index.
     *
     * @param index
     * @return Genotype
     */
    public Genotype getGenotype(int index) {
        return  _genotypes.get(index);
    }

    public int getPopulationSize() {
        return _genotypes.size();
    }
}