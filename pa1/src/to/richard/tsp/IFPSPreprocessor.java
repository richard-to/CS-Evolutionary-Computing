package to.richard.tsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

/**
 * This interface is used by the FitnessProportionateSelector.
 *
 * This basically a hook, to allow preprocessing of fitness values to make them
 * work better (avoiding premature convergence and/or stagnation)
 * for roulette wheel and SUS algorithms.
 */
public interface IFPSPreprocessor {
    public List<Pair<Double, Genotype>> preprocess(List<Pair<Double, Genotype>> genotypeFitnessList);
}
