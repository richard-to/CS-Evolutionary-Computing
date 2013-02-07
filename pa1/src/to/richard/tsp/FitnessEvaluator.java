package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/6/13
 */

import java.util.List;

/**
 * Evaluates fitness of a genotype using CostMatrix.
 *
 * Class is immutable. If a new CostMatrix needs to be used, a new one
 * should be instantiated.
 *
 * Needs to be named more specifically. CostMatrixFitnessEvaluator?
 * Probably need to add an interface IFitnessEvaluator later.
 */
public class FitnessEvaluator {
    private CostMatrix _costMatrix;
    public FitnessEvaluator(CostMatrix costMatrix) {
        _costMatrix = costMatrix;
    }

    /**
     * Evaluate the fitness of a genotype using cost matrix.
     * @param genotype
     * @return
     */
    public int evaluate(Genotype genotype) {
        int home = 0;
        int fromAllele = home;
        int fitness = 0;

        for (int toAllele : genotype) {
            fitness += _costMatrix.getCost(fromAllele, toAllele);
            fromAllele = toAllele;
        }
        fitness += _costMatrix.getCost(fromAllele, home);
        return fitness;
    }
}
