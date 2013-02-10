package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

import java.util.HashMap;
import java.util.List;

/**
 * Evaluates fitness of a genotype using CostMatrix.
 *
 * Class is immutable. If a new CostMatrix needs to be used, a new instance
 * should be instantiated.
 *
 * FitnessEvaluator will cache fitness values based on genotype toString() value;
 *
 * This class works specifically for TSP type problems, so probably should be renamed!
 */
public class FitnessEvaluator {
    private CostMatrix _costMatrix;
    private HashMap<Genotype, Double> fitnessCache;

    public FitnessEvaluator(CostMatrix costMatrix) {
        _costMatrix = costMatrix;
        fitnessCache = new HashMap<Genotype, Double>();
    }

    public double evaluate(Genotype genotype) {
        Allele home = new Allele(0);
        Allele fromAllele = home;
        double fitness = 0.0;

        if (fitnessCache.containsKey(genotype)) {
            fitness = fitnessCache.get(genotype);
        } else {
            for (Allele toAllele : genotype) {
                fitness += _costMatrix.getCost(fromAllele, toAllele);
                fromAllele = toAllele;
            }
            fitness += _costMatrix.getCost(fromAllele, home);
            fitnessCache.put(genotype, fitness);
        }
        return fitness;
    }

    public Pair<Double, Genotype> evaluateAsPair(Genotype genotype) {
        double fitness = evaluate(genotype);
        return new Pair<Double, Genotype>(fitness, genotype);
    }
}
