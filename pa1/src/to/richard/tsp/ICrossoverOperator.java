package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * This interface is limited to 2 parents, so may not be
 * flexible enough for the case where there is more than 2 parents.
 */
public interface ICrossoverOperator {
    public Recombinator.OFFSPRING numberOfOffspring();
    public List<Genotype> crossover(Genotype parent1, Genotype parent2, IRandom random);
}
