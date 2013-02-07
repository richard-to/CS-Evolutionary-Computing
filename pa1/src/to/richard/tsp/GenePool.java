package to.richard.tsp;

import java.util.HashSet;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Represents a pool of genes for the current generation.
 *
 * Gene pool does not contain duplicates.
 */
public class GenePool {

    private List<Genotype> _genotypes;

    /**
     * GenePool constructor
     *
     * If genotype list contains duplicates, an error be thrown.
     * @param genotypes
     */
    public GenePool(List<Genotype> genotypes) {
        HashSet<Genotype> duplicateCheck = new HashSet<Genotype>();
        if (!duplicateCheck.addAll(_genotypes)) {
            throw new Errors.DuplicateObjectFound();
        }
        _genotypes = genotypes;
    }

    /**
     * Gets a genotype at the specified index.
     *
     * An IndexOutOfBounds exception is thrown.
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