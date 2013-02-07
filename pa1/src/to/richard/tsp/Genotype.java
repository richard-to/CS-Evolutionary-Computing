package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

import java.util.Arrays;
import java.util.Iterator;

/**
 * Immutable Genotype for Traveling Sales Person problem.
 *
 * Representation of genes is permutations.
 *
 * Alleles must be distinct. This is not checked by Genotype.
 * Use GenotypeValidator to validate Genotype (length, valid values, distinctness).
 * Fitness is evaluated using a FitnessEvaluator.
 */
public class Genotype implements Iterable<Integer> {

    protected int[] _genes;
    protected String _genotypeString;

    /**
     * Protected constructor. Only used to make inheritance work.
     */
    protected Genotype() {}

    /**
     * Construct a genotype with allele values for genes.
     */
    public Genotype(int[] alleles) {
        _genes = Arrays.copyOf(alleles, alleles.length);
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Copy constructor. Mainly needed to MutableGenotype copy method.
     * Otherwise kind of pointless.
     */
    public Genotype(Genotype genotype) {
        _genes = Arrays.copyOf(genotype._genes.clone(), genotype._genes.length);
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Helper to build a string representation of genotype. This is basically
     * a sequence of alleles in string format instead of array format.
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
     * Index starts at 0. Out of bounds exception is thrown if
     * index is out bounds.
     */
    public int getAllele(int geneIndex) {
        if (geneIndex < 0 || geneIndex >= _genes.length) {
            throw new IndexOutOfBoundsException();
        }
        return _genes[geneIndex];
    }

    /**
     * Gets length of genes.
     */
    public int length() {
        return _genes.length;
    }

    /**
     * Gets the gene sequence in string format. Returns a copy of the string.
     */
    public String toString() {
        return new String(_genotypeString);
    }

    /**
     * Copies genotype and returns mutable version.
     */
    public MutableGenotype copyMutable() {
        return new MutableGenotype(this);
    }

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
