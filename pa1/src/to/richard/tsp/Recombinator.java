package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Performs recombination step using specified recombination strategy.
 *
 * Recombination/Crossover rate works the same as mutation rate from
 * Mutator class.
 *
 */
public class Recombinator {
    private IRandom _random;
    private double _recombinationRate;
    private ICrossoverOperator _crossoverOperator;

    public Recombinator(double recombinationRate, ICrossoverOperator crossoverOperator, IRandom random) {
        _random = random;
        _recombinationRate = recombinationRate;
        _crossoverOperator = crossoverOperator;
    }

    /**
     * In the case of an odd number of parents (is that allowed?),
     * those will just be passed along asexually.
     *
     * This is kind of a problem in the case of 100% recombination since
     * 1 will be left out.
     *
     * @param genotypes
     * @return
     */
    public List<Genotype> recombine(List<Genotype> genotypes) {
        double probability = 0.0;
        int index1 = 0;
        int index2 = 0;
        int pairs = genotypes.size() / 2;
        int remainders = genotypes.size() % 2;
        ArrayList<Genotype> newGenotypes = new ArrayList<Genotype>();

        for (int i = 0; i < pairs; i++) {
            index1 = i * 2;
            index2 = i * 2 + 1;
            probability = _random.nextDouble();
            if (probability < _recombinationRate) {
                newGenotypes.addAll(_crossoverOperator.crossover(genotypes.get(index1), genotypes.get(index2), _random));
            } else {
                newGenotypes.add(genotypes.get(index1));
                newGenotypes.add(genotypes.get(index2));
            }
        }

        for (int i = genotypes.size() - remainders; i < genotypes.size(); i++) {
            newGenotypes.add(genotypes.get(i));
        }

        return newGenotypes;
    }
}