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
 * Class is immutable. If a new CostMatrix needs to be used, a new one
 * should be instantiated.
 *
 * FitnessEvaluator will cache fitness values based on genotype toString() value;
 *
 * Needs to be named more specifically. CostMatrixFitnessEvaluator?
 * Probably need to add an interface IFitnessEvaluator later.
 */
public class FitnessEvaluator {
    private CostMatrix _costMatrix;
    private HashMap<String, Double> fitnessCache;

    public FitnessEvaluator(CostMatrix costMatrix) {
        _costMatrix = costMatrix;
        fitnessCache = new HashMap<String, Double>();
    }

    /**
     * Evaluate the fitness of a genotype using cost matrix.
     * @param genotype
     * @return
     */
    public double evaluate(Genotype genotype) {
        int home = 0;
        int fromAllele = home;
        double fitness = 0.0;

        if (fitnessCache.containsKey(genotype.toString())) {
            fitness = fitnessCache.get(genotype.toString());
        } else {
            for (int toAllele : genotype) {
                fitness += _costMatrix.getCost(fromAllele, toAllele);
                fromAllele = toAllele;
            }
            fitness += _costMatrix.getCost(fromAllele, home);
            fitnessCache.put(genotype.toString(), fitness);
        }
        return fitness;
    }
}
