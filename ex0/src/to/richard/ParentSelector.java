package to.richard;

import java.util.ArrayList;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Parent Selector emulates simple parent selection.
 *
 * Selects genotypes to act as parents for recombination step.
 */
public class ParentSelector {

    private IRandom _random;

    public ParentSelector(IRandom random) {
        _random = random;
    }

    public ArrayList<GenotypeParents> selectParents(GenePool genePool, int parentsCount) {

        ArrayList<GenotypeParents> genotypeParentsList = new ArrayList<GenotypeParents>();
        Genotype parent1 = null;
        Genotype parent2 = null;

        ArrayList<Genotype> genotypeList = genePool.getGenotypeList();
        double totalFitness = 0.0;

        ProbabilityDistribution<Genotype> genotypeProbabilityDistribution =
                new ProbabilityDistribution<Genotype>(_random);
        double parentProbability = 0.0;

        for (Genotype genotype : genotypeList) {
            totalFitness += genotype.getFitness();
        }

        for (Genotype genotype : genotypeList) {
            parentProbability = genotype.getFitness() / totalFitness;
            genotypeProbabilityDistribution.add(parentProbability, genotype);
        }

        for (int i = 0; i < parentsCount; i++) {
            parent1 = genotypeProbabilityDistribution.getRandom().clone();
            parent2 = genotypeProbabilityDistribution.getRandom().clone();
            genotypeParentsList.add(new GenotypeParents(parent1, parent2));
        }
        return genotypeParentsList;
    }
}
