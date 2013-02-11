package to.richard.tsp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/11/13
 */

/**
 * Implementation of Elitism using Aged-based replacement.
 *
 * Replace fittest parent with worst offspring. If no worst offspring,
 * don't do replacement.
 */
public class ElitismSurvivorSelection implements ISurvivorSelector {

    private FitnessEvaluator _fitnessEvaluator;
    private Comparator<Pair<Double, Genotype>> _comparator;

    public ElitismSurvivorSelection(
            FitnessEvaluator fitnessEvaluator, Comparator<Pair<Double, Genotype>> comparator) {
        _fitnessEvaluator = fitnessEvaluator;
        _comparator = comparator;
    }

    @Override
    public List<Genotype> replace(List<Genotype> parents, List<Genotype> offspring) {
        return offspring;
    }
}
