package to.richard;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

import java.util.List;

/**
 * Analyzes fitness of current genotype.
 */
public class FitnessAnalyzer {

    /**
     * Analyzes fitness of current generation.
     *
     * @param genotypeList
     */
    public FitnessResult analyze(List<Genotype> genotypeList) {
        double sum = 0;
        double avg = 0;
        double max = 0;
        double fitness = 0;
        int count = genotypeList.size();

        for (Genotype genotype : genotypeList) {
            fitness = genotype.getFitness();
            sum += fitness;
            if (fitness > max) {
                max = fitness;
            }
        }

        avg = sum / count;

        return new FitnessResult(sum, avg, max);
    }
}
