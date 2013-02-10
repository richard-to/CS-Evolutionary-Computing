package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Implementation of cycle crossover operator.
 *
 * 1. Start with P1 index 0
 * 2. Look at P2 index 0
 * 3. Find the P2_0 allele in P1
 * 4. Add allele to cycle
 * 5. Repeat until you reach P1_0
 * 6. Next go to the next free gene position at P1 and repeat process above. Store in different cycle.
 * 7. For every other cycle, swap alleles.
 */
public class CycleCrossover implements ICrossoverOperator {
    public Recombinator.OFFSPRING numberOfOffspring() {
        return Recombinator.OFFSPRING.PAIR;
    }

    public List<Genotype> crossover(Genotype genotype1, Genotype genotype2, IRandom random) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
