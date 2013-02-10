package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

/**
 * Returns the genotype with the highest fitness value. In the case of
 * ties, the first genotype gets returned.
 */
public class FitnessMaximizationComparator implements IFitnessComparator {

    @Override
    public Pair<Double, Genotype> compare(Pair<Double, Genotype> pair1, Pair<Double, Genotype> pair2) {
        return (pair1.getFirstValue() >= pair2.getFirstValue()) ? pair1 : pair2;
    }
}
