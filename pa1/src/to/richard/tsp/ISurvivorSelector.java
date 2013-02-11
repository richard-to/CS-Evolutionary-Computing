package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/11/13
 */

import java.util.List;

/**
 * Interface for Survivor selection stage
 */
public interface ISurvivorSelector {
    public List<Genotype> replace(List<Genotype> parents, List<Genotype> offspring);
}
