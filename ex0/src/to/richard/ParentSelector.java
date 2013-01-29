package to.richard;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Parent Selector
 */
public class ParentSelector {

    private Random _random;

    public ParentSelector(Random random) {
        _random = random;
    }

    public void selectParents(GenePool genePool, int parentsCount) {
        ArrayList<Genotype> genotypeList = genePool.getGenotypeList();
        ProbabilityDistribution<Genotype> genotypeProbabilityDistribution =
                new ProbabilityDistribution<Genotype>(_random);
    }
}
