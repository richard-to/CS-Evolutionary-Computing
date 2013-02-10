package to.richard.tsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Implements parent selection using Fitness Proportionate selection.
 *
 * This implementation accepts a handful parameters to allow some interesting combinations.
 *
 * FPS optimizations such as windowing, sigma scaling, and linear normalization (Rank Selection?)
 * are possible using the IFPSProcessor interface.
 *
 * Concrete implementations are FPSWindowing, FPSSigmaScaling, FPSLinearNormalization.
 *
 * For minimization problems, we need to make the lowest fitness have the largest value.
 * In this case, we will do so by adding up the total value and subtracting the real fitness
 * value. This may not be the best solution, but it will at least order the fitness values correctly.
 *
 * Concrete implementation is FPSMinimization.
 *
 * A sampling algorithm can be provided using the ISampler interface. The options here are the
 * roulette wheel algorithm and stochastic universal sampling.
 *
 * Concrete implements are RouletteWheel and StochasticUniversalSampling.
 *
 * The other parameter is a FitnessEvaluator class. This provides a way to calculate the fitness of a
 * genotype since genotypes cannot calculate that value themselves in my implementation.
 */
public class FitnessProportionateSelector implements ISelector {
    private IRandom _random;
    private ISampler<Genotype> _sampler;
    private FitnessEvaluator _fitnessEvaluator;
    private List<IFPSPreprocessor> _fitnessPreprocessors;

    public FitnessProportionateSelector(
            FitnessEvaluator fitnessEvaluator, ISampler<Genotype> sampler, IRandom random) {
        init(fitnessEvaluator, sampler, random, new ArrayList<IFPSPreprocessor>());
    }

    public FitnessProportionateSelector(
            FitnessEvaluator fitnessEvaluator, ISampler<Genotype> sampler,
            IRandom random,  List<IFPSPreprocessor> fitnessPreprocessors) {
        init(fitnessEvaluator, sampler, random, fitnessPreprocessors);
    }

    private void init(FitnessEvaluator fitnessEvaluator, ISampler<Genotype> sampler,
                 IRandom random, List<IFPSPreprocessor> fitnessPreprocessors) {
        _random = random;
        _sampler = sampler;
        _fitnessEvaluator = fitnessEvaluator;
        _fitnessPreprocessors = fitnessPreprocessors;
    }
    public List<Genotype> selectParents(List<Genotype> genotypes) {
        int populationSize = genotypes.size();
        double fitness = 0.0;
        ArrayList<Pair<Double, Genotype>> fitnessPairs = new ArrayList<Pair<Double, Genotype>>();
        for (Genotype genotype : genotypes) {
            fitness = _fitnessEvaluator.evaluate(genotype);
            fitnessPairs.add(new Pair<Double, Genotype>(fitness, genotype));
        }
        return (List<Genotype>)_sampler.sample(fitnessPairs, populationSize);
    }
}
