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
 * This basically a hook, to allow transformation of fitness values to make them
 * work better (avoiding premature convergence and/or stagnation)
 * for roulette wheel and SUS algorithms.
 */
public interface IFPSTransform {
    public List<Pair<Double, Genotype>> transform(List<Pair<Double, Genotype>> genotypeFitnessList);
}
