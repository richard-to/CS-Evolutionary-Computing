package to.richard.tsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Implements parent selection using Fitness Proportionate selection.
 * Currently only use basic roulette wheel implementation.
 */
public class FitnessProportionateSelector {
    private IRandom _random;
    private ISampler<Genotype> _sampler;
    private FitnessEvaluator _fitnessEvaluator;

    public FitnessProportionateSelector(
            IRandom random, ISampler<Genotype> sampler, FitnessEvaluator fitnessEvaluator) {
        _random = random;
        _sampler = sampler;
        _fitnessEvaluator = fitnessEvaluator;
    }

    /**
     * Selects parents to create children for next generation.
     * @param genotypes
     * @return parents to create children for next generation
     */
    public List<Genotype> selectParents(List<Genotype> genotypes) {
        int populationSize = genotypes.size();
        double fitness = 0.0;
        ArrayList<Pair<Double, Genotype>> fitnessPairs = new ArrayList<Pair<Double, Genotype>>();
        for (Genotype genotype : genotypes) {
            fitness = _fitnessEvaluator.evaluate(genotype);
            fitnessPairs.add(new Pair<Double, Genotype>(fitness, genotype));
        }
        return (List<Genotype>)_sampler.sample(fitnessPairs, populationSize).clone();
    }
}
