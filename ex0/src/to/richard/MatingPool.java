package to.richard;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Mating Pool that uses 1-point crossover.
 */
public class MatingPool {

    IRandom _random;

    /**
     * Constructs mating pool.
     * @param random Used to randomly select crossover points.
     */
    public MatingPool(IRandom random) {
        _random = random;
    }

    /**
     * Performs recombination on a list of parents.
     *
     * Specifically performing 1-point crossover.
     * This means that index 0 and bit string length - 1 are excluded.
     * We take will swap everything to the left of crossover point.
     * This means that crossover point is exclusive.
     *
     * Example: If crossover point four is selected. We will partion as so: 0 1 0 1 | 0 and 1 1 1 0 | 1.
     * Then we swap to everything to the right and get the following 0 1 0 1 | 1 and 1 1 1 0 | 0.
     *
     * @param genotypeParentsList
     */
    public List<Genotype> recombine(List<GenotypeParents> genotypeParentsList) {
        int crossoverFloor = 1;
        int crossoverMax = 1;
        int crossoverIndex = 1;

        ArrayList<Genotype> genotypeList = new ArrayList<Genotype>();

        Genotype parent1 = null;
        Genotype parent2 = null;
        int[] parent1Genes = null;
        int[] parent2Genes = null;

        for (GenotypeParents genotypeParents : genotypeParentsList) {
            parent1 = genotypeParents.getParent1();
            parent2 = genotypeParents.getParent2();
            crossoverMax = parent1.getGenes().length - 1;
            crossoverIndex = _random.nextInt(crossoverMax - crossoverFloor) + crossoverFloor;
            parent1Genes = parent1.getGenes();
            parent2Genes = parent2.getGenes();
            for (int i = crossoverIndex + 1; i <= crossoverMax; ++i) {
                int tempGene = parent1Genes[i];
                parent1Genes[i] = parent2Genes[i];
                parent2Genes[i] = tempGene;
            }
            genotypeList.add(new Genotype(parent1Genes));
            genotypeList.add(new Genotype(parent2Genes));
        }

        return genotypeList;
    }
}
