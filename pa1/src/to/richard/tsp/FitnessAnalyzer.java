package to.richard.tsp;

import java.util.Comparator;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/11/13
 */

/**
 * Analyzes fitness of current genotype population.
 */
public class FitnessAnalyzer {

    private FitnessEvaluator _fitnessEvaluator;
    private Comparator<Pair<Double, Genotype>> _comparator;

    public FitnessAnalyzer(FitnessEvaluator fitnessEvaluator, Comparator<Pair<Double, Genotype>> comparator) {
        _fitnessEvaluator = fitnessEvaluator;
        _comparator = comparator;
    }

    /**
     * Analyzes fitness of current generation.
     *
     * @param genotypeList
     */
    public FitnessResult analyze(List<Genotype> genotypeList) {
        double sum = 0;
        double avg = 0;
        double best = 0;
        int count = genotypeList.size();
        Pair<Double, Genotype> bestPair = null;
        Pair<Double, Genotype> currentPair = null;

        for (Genotype genotype : genotypeList) {
            currentPair = _fitnessEvaluator.evaluateAsPair(genotype);
            sum += currentPair.getFirstValue();
            if (bestPair == null || _comparator.compare(currentPair, bestPair) > 0) {
                bestPair = currentPair;
            }
        }

        avg = sum / count;
        best = bestPair.getFirstValue();

        return new FitnessResult(sum, avg, best, bestPair.getSecondValue());
    }
}
