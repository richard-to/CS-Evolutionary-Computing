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
 *
 * Currently there is the issue of using alleles that are
 * primitive integers. This prevents the use of nulls,
 * and forces 0 to be considered an empty value.
 *
 * This may not work for all cases. Maybe add an Allele class.
 */
public class MutableGenotype extends Genotype {

    /**
     * Default constructor. Need to supply gene length at minimum,
     * since this is not mutable.
     */
    public MutableGenotype(int length) {
        _genes = new Allele[length];
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Construct a genotype with allele values for genes.
     */
    public MutableGenotype(Allele[] alleles) {
        super(alleles);
    }

    /**
     * Copy constructor
     */
    public MutableGenotype(Genotype genotype) {
        _genes = Arrays.copyOf(genotype._genes.clone(), genotype._genes.length);
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Sets allele values for entire gene.
     * The length of alleles must match length of genes. If not
     * an error is thrown.
     */
    public void setAlleles(Allele[] alleles) {
        if (alleles.length != _genes.length) {
            throw new Errors.AllelesDoNotMatchGenes();
        }
        _genes = Arrays.copyOf(alleles, alleles.length);
        _genotypeString = buildGenotypeString(_genes);
    }

    /**
     * Sets allele value by gene index.
     *
     * If index is out of bounds, an error is thrown.
     */
    public void setAllele(Allele value, int geneIndex) {
        if (geneIndex < 0 || geneIndex >= _genes.length) {
            throw new IndexOutOfBoundsException();
        }
        _genes[geneIndex] = value;
        _genotypeString = buildGenotypeString(_genes);
    }


    /**
     * Checks if an allele exists. If position is out of bounds, an
     * ArrayIndexOutOfBounds exception will be thrown.
     */
    public boolean hasAlleleAt(int pos) {
        Allele allele = getAllele(pos);
        return (allele != null) ? true : false;
    }

    /**
     * Makes an immutable copy of genotype.
     */
    public Genotype copy() {
        return new Genotype(this);
    }
}
