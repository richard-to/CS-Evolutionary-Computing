package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/5/13
 */

import java.util.Arrays;

/**
 * Mutable Genotype that allows a genotype to be
 * built incrementally and modified.
 *
 * This is useful for Recombination, Mutation, etc.
 */
public class MutableGenotype extends Genotype {

    /**
     * Default constructor. Need to supply gene length at minimum,
     * since this is not mutable.
     *
     * @param length
     */
    public MutableGenotype(int length) {
        _genes = new int[length];
    }

    /**
     * Construct a genotype with allele values for genes.
     *
     * @param alleles
     */
    public MutableGenotype(int[] alleles) {
        super(alleles);
    }

    /**
     * Copy constructor
     * @param genotype
     */
    public MutableGenotype(Genotype genotype) {
        _genes = Arrays.copyOf(genotype._genes.clone(), genotype._genes.length);
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Sets allele values for entire gene.
     * The length of alleles must match length of genes.
     * @param alleles
     */
    public void setAlleles(int[] alleles) {
        if (alleles.length != _genes.length) {
            throw new Errors.AllelesDoNotMatchGenes();
        }
        _genes = Arrays.copyOf(alleles, alleles.length);
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Sets allele value by gene index.
     * @param value
     * @param geneIndex 0 is the start index
     */
    public void setAllele(int value, int geneIndex) {
        if (geneIndex < 0 || geneIndex >= _genes.length) {
            throw new IndexOutOfBoundsException();
        }
        _genes[geneIndex] = value;
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Makes an immutable copy of genotype.
     * @return Genotype
     */
    public Genotype copy() {
        return new Genotype(this);
    }
}
