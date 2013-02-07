package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

import java.util.Iterator;

/**
 * Immutable Genotype for Traveling Sales Person problem.
 *
 * Representation of genes is permutations.
 * Alleles represent the index of the city in the cost matrix.
 * Alleles must be distinct. This is not checked by Genotype.
 * Use GenotypeValidator to validate Genotype (length, valid values, distinctness).
 * Fitness is evaluated using a FitnessEvaluator.
 */
public class Genotype implements Iterable<Integer> {

    protected int[] _genes;
    protected String _genotypeString;

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
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Builds string representation of genotype
     *
     * @param genes
     * @return
     */
    protected String buildGenotypeString(int[] genes) {
        StringBuilder genotypeStringBuilder = new StringBuilder();
        for (int i = 0; i < genes.length; i++) {
            genotypeStringBuilder.append(genes[i]);
        }
        return genotypeStringBuilder.toString();
    }

    /**
     * Gets allele value by gene index.
     *
     * @param geneIndex 0 is the start index
     * @return Allele value
     */
    public int getAllele(int geneIndex) {
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
     * Gets the gene sequence in string format. Returns a copy of the string.
     * @return
     */
    public String toString() {
        return new String(_genotypeString);
    }

    /**
     * Copies genotype and returns mutable version.
     *
     * @return MutableGenotype
     */
    public MutableGenotype copyMutable() {
        return new MutableGenotype(_genes.clone());
    }

    /**
     * Implements "geneIterator."
     * For now, just leaving it as Integer for simplicity.
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> geneIterator = new Iterator<Integer>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < length();
            }

            @Override
            public Integer next() {
                return getAllele(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return geneIterator;
    }
}
