package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

import java.util.Comparator;

/**
 * Implements java Comparator interface for custom sorting and comparison cases.
 *
 * This case compares for highest fitness value (Min to Max)
 */
public class FitnessMaximizationComparator implements Comparator<Pair<Double, Genotype>> {
    @Override
    public int compare(Pair<Double, Genotype> doubleGenotypePair, Pair<Double, Genotype> doubleGenotypePair2) {
        if (doubleGenotypePair.getFirstValue() > doubleGenotypePair2.getFirstValue()) {
            return 1;
        } else if (doubleGenotypePair.getFirstValue() < doubleGenotypePair2.getFirstValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
