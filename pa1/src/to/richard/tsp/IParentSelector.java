package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Interface for different parent selection algorithms.
 *
 * Ie. FPS, Tournament
 */
public interface IParentSelector {
    public List<Genotype> selectParents(List<Genotype> genotypes);
}
