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
 * Concrete implementations are FPSWindowing, FPSLinearNormalization, FPSExponential, FPSSigmaScaling (not implemented)
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
    private List<IFPSTransform> _transforms;

    public FitnessProportionateSelector(
            FitnessEvaluator fitnessEvaluator, ISampler<Genotype> sampler, IRandom random) {
        init(fitnessEvaluator, sampler, random, new ArrayList<IFPSTransform>());
    }

    public FitnessProportionateSelector(
            FitnessEvaluator fitnessEvaluator, ISampler<Genotype> sampler,
            IRandom random,  List<IFPSTransform> transforms) {
        init(fitnessEvaluator, sampler, random, transforms);
    }

    private void init(FitnessEvaluator fitnessEvaluator, ISampler<Genotype> sampler,
                 IRandom random, List<IFPSTransform> transforms) {
        _random = random;
        _sampler = sampler;
        _fitnessEvaluator = fitnessEvaluator;
        _transforms = transforms;
    }

    public List<Genotype> selectParents(List<Genotype> genotypes) {
        int populationSize = genotypes.size();
        double fitness = 0.0;

        List<Pair<Double, Genotype>> fitnessPairs = new ArrayList<Pair<Double, Genotype>>();

        for (Genotype genotype : genotypes) {
            fitness = _fitnessEvaluator.evaluate(genotype);
            fitnessPairs.add(new Pair<Double, Genotype>(fitness, genotype));
        }

        for (IFPSTransform transform : _transforms) {
            fitnessPairs = transform.transform(fitnessPairs);
        }

        return _sampler.sample(fitnessPairs, populationSize);
    }
}
