package to.richard;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Represents a pair of parent genotypes.
 */
public class GenotypeParents {

    private Genotype _parent1;
    private Genotype _parent2;

    public GenotypeParents(Genotype parent1, Genotype parent2) {
        _parent1 = parent1;
        _parent2 = parent2;
    }

    public Genotype getParent1() {
        return _parent1;
    }

    public Genotype getParent2() {
        return _parent2;
    }
}
