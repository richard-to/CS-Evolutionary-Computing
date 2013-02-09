package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Validates Genotype based on the following
 * criteria: gene length, allele values, distinct values.
 *
 * This data structure is Immutable. So settings can only be set
 * during initialization.
 *
 * This is useful to make sure all genotypes conform to the same
 * "type."
 *
 * Probably should named better since this works
 * specifically for Permutations and a CostMatrix.
 *
 * Also this class currently handles only allele values that are integers.
 */
public class GenotypeValidator {

    private int _geneLength;
    private HashSet<Allele> _alleleValueSet;

    /**
     * Constructor accepts a cost matrix, which will
     * allow the validator to get the needed validation
     * criteria (length, valid alleles, distinctness)
     */
    public GenotypeValidator(CostMatrix costMatrix) {
        _alleleValueSet = new HashSet<Allele>();
        Allele[] alleles = costMatrix.getAlleles();
        for (Allele allele : alleles) {
            _alleleValueSet.add(allele);
        }
        _geneLength = _alleleValueSet.size();
    }

    public boolean validate(Genotype genotype) {
        return validate(Arrays.asList(new Genotype[]{genotype}));
    }

    public boolean validate(List<Genotype> genotypes) {
        HashSet<Allele> duplicationCheck = new HashSet<Allele>();
        for (Genotype genotype : genotypes) {
            duplicationCheck.clear();

            if (genotype.length() != _geneLength) {
                return false;
            }

            for (Allele allele : genotype) {
                if (!_alleleValueSet.contains(allele)) {
                    return false;
                }

                if (!duplicationCheck.add(allele)) {
                    return false;
                }
            }
        }
        return true;
    }
}
