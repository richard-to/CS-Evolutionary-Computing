package to.richard.tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

/**
 * Parent selection using tournament selection.
 *
 * Randomly select two genotypes. The one with best fitness is selected.
 * This is repeated until we have reached the specified population size.
 *
 * Population size is currently determined by the size of the genotype list
 * passed into the selectParents method.
 *
 * Note: I think in Tournament selection more than two genotypes can be selected,
 * but I am just going with two for now.
 */
public class TournamentSelector implements ISelector {
    private IRandom _random;
    private FitnessEvaluator _fitnessEvaluator;

    public TournamentSelector(IRandom random, FitnessEvaluator fitnessEvaluator) {
        _random = random;
        _fitnessEvaluator = fitnessEvaluator;
    }

    public List<Genotype> selectParents(List<Genotype> genotypes) {
        int populationSize = genotypes.size();
        ArrayList<Genotype> parentGenotypes = new ArrayList<Genotype>();
        Genotype parent1;
        Genotype parent2;

        while (parentGenotypes.size() < populationSize) {
            parent1 = genotypes.get(_random.nextInt(populationSize));
            parent2 = genotypes.get(_random.nextInt(populationSize));
            if (_fitnessEvaluator.evaluate(parent1) > _fitnessEvaluator.evaluate(parent2)) {
                parentGenotypes.add(parent1);
            } else {
                parentGenotypes.add(parent2);
            }
        }
        return (List<Genotype>)parentGenotypes;
    }
}
