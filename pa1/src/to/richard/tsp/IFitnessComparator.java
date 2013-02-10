package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

/**
 * Interface used to compare fitness values. Used by TournamentSelector.
 *
 * Mainly used for Maximization or Minimization.
 */
public interface IFitnessComparator {

    /**
     * Compares 2 genotypes and their fitness values. Returns the genotype pair
     * with the best fitness.
     *
     * In the case of ties, either one can be returned. This can be random or
     * merely just selecting the first every time.
     */
    public Pair<Double, Genotype> compare(Pair<Double, Genotype> pair1, Pair<Double, Genotype> pair2);
}
