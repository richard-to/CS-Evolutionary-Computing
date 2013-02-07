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
    private HashSet<Integer> _alleleValueSet;

    /**
     * Constructor accepts a cost matrix, which will
     * allow the validator to get the needed validation
     * criteria.
     *
     * @param costMatrix
     */
    public GenotypeValidator(CostMatrix costMatrix) {
        _alleleValueSet = new HashSet<Integer>();
        for (int i = 1; i < costMatrix.size(); i++) {
            _alleleValueSet.add(i);
        }
        _geneLength = _alleleValueSet.size();
    }

    /**
     * Validates a single genotype.
     * @param genotype
     * @return
     */
    public boolean validate(Genotype genotype) {
        return validate(Arrays.asList(new Genotype[]{genotype}));
    }

    /**
     * Validates a list of genotypes.
     * @param genotypes
     * @return
     */
    public boolean validate(List<Genotype> genotypes) {
        HashSet<Integer> duplicationCheck = new HashSet<Integer>();
        for (Genotype genotype : genotypes) {
            duplicationCheck.clear();

            if (genotype.length() != _geneLength) {
                return false;
            }

            for (int allele : genotype) {
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
