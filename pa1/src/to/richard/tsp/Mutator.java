package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Handles some boiler plate initialization for mutation.
 *
 * Mutation rate should be between 0-1. But this is not checked.
 * A mutation less than 0 means that same as a 0% probability. And a mutation
 * rate greater than 1 means the same as 100% probability.
 *
 * A genotype will be mutated if random probability is less than mutation rate value.
 *
 * Here mutation rate applies to entire genotype, since this is for permutation. So this
 * is different than checking for mutation at the gene level that could be used in
 * bit representations.
 *
 * A mutation algorithm needs to be passed in. Ie. Swap, Scramble, or Inversion for this case.
 */
public class Mutator {

    private IRandom _random;
    private double _mutationRate;
    private IMutationOperator _mutationOperator;

    public Mutator(double mutationRate, IMutationOperator mutationOperator, IRandom random) {
        _random = random;
        _mutationRate = mutationRate;
        _mutationOperator = mutationOperator;
    }

    public List<Genotype> mutate(List<Genotype> genotypes) {
        double probability = 0.0;
        ArrayList<Genotype> newGenotypes = new ArrayList<Genotype>();
        for (Genotype genotype : genotypes) {
            probability = _random.nextDouble();
            if (probability < _mutationRate) {
                newGenotypes.add(_mutationOperator.mutate(genotype, _random));
            } else {
                newGenotypes.add(genotype);
            }
        }
        return newGenotypes;
    }
}
