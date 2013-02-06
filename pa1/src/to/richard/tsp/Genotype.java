package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

/**
 * Immutable Genotype for Traveling Sales Person problem.
 *
 * Representation of genes is permutations.
 * Alleles represent the index of the city in the cost matrix.
 * Alleles must be distinct. This is not checked by Genotype.
 * Use GenotypeValidator to validate Genotype (length, valid values, distinctness).
 */
public class Genotype {

    protected int[] _genes;

    /**
     * Protected constructor. Only used for inheritance.
     */
    protected Genotype() {}

    /**
     * Construct a genotype with allele values for genes.
     *
     * @param alleles
     */
    public Genotype(int[] alleles) {
        _genes = alleles.clone();
    }

    /**
     * Gets allele value by gene index.
     *
     * @param geneIndex 0 is the start index
     * @return Allele value
     * @throws IndexOutOfBoundsException
     */
    public int getAllele(int geneIndex) throws IndexOutOfBoundsException {
        if (geneIndex < 0 || geneIndex >= _genes.length) {
            throw new IndexOutOfBoundsException();
        }
        return _genes[geneIndex];
    }

    /**
     * Gets length of genes.
     * @return length of genes
     */
    public int length() {
        return _genes.length;
    }

    /**
     * Copies genotype and returns mutable version.
     *
     * @return MutableGenotype
     */
    public MutableGenotype copyMutable() {
        return new MutableGenotype(_genes.clone());
    }
}