package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Implementation of OrderCrossover operator
 *
 * 1. Pick two crossover points.
 * 2. Move alleles between crossover points in P1 to corresponding offspring gene positions.
 * 3. Now cycle through P2 alleles starting from crossover point 2.
 * 4. Start index of offspring 2 at crossover point 2
 * 5. If the allele is not in the current offspring, add it to the gene position at current index. Increment index.
 * 6. Loop through P2 until crossover point 2 is reached again.
 * 7. For second offspring, reverse process.
 */
public class OrderCrossover implements ICrossoverOperator {

    public Recombinator.OFFSPRING numberOfOffspring() {
        return Recombinator.OFFSPRING.PAIR;
    }

    public List<Genotype> crossover(Genotype genotype1, Genotype genotype2, IRandom random) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
