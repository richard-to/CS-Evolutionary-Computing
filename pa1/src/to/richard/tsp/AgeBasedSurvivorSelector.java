package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/11/13
 */

/**
 * Implementation of age based survivor selection.
 *
 * Aged-based survivor selection moves the newest genotypes to
 * the next generation. Basically this means the offspring.
 */
public class AgeBasedSurvivorSelector implements ISurvivorSelector {
    @Override
    public List<Genotype> replace(List<Genotype> parents, List<Genotype> offspring) {
        return offspring;
    }
}
