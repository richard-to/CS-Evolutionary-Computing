package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Implementation of edge crossover operator.
 *
 * 1. Create edge table from both parents. Adjacency list.
 * 2. Pick a random allele/gene from table.
 * 3. Add allele to offspring.
 * 4. Remove the references to allele from the adjacency list.
 * 5. Look at adjacent edges for selected allele. Pick using the following criteria.
 *    - Pick common edge. This is the edge represented by +. Both parents have same edge.
 *    - Pick entry with shortest list.
 *    - If more than 1 entry. Randomly pick one.
 * 6. If empty list, choose another random element and repeat.
 */
public class EdgeCrossover implements ICrossoverOperator {

    public Recombinator.OFFSPRING numberOfOffspring() {
        return Recombinator.OFFSPRING.SINGLE;
    }

    public List<Genotype> crossover(Genotype genotype1, Genotype genotype2, IRandom random) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
