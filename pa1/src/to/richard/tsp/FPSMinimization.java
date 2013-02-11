package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Preprocesses fitness/genotype pairs so that lower values
 * have higher values.
 *
 * This is useful for FitnessProportionate selection algorithms.
 *
 * The algorithm works somewhat, but not sure how close it would be to original fitness values.
 * Seems like the distance between fitness values get closer percentage-wise.
 *
 * 1. Find total fitness
 * 2. Subtract fitness from total fitness
 */
public class FPSMinimization implements IFPSTransform {

    @Override
    public List<Pair<Double, Genotype>> transform(List<Pair<Double, Genotype>> genotypeFitnessList) {
        double totalFitness = 0;
        ArrayList<Pair<Double, Genotype>> newList = new ArrayList<Pair<Double, Genotype>>();

        for (Pair<Double, Genotype> pair : genotypeFitnessList) {
            totalFitness += pair.getFirstValue();
        }

        for (Pair<Double, Genotype> pair : genotypeFitnessList) {
            newList.add(new Pair<Double, Genotype>(totalFitness - pair.getFirstValue(), pair.getSecondValue()));
        }
        return newList;
    }
}
