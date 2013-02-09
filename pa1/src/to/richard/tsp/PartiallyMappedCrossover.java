package to.richard.tsp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Partially Mapped Crossover implementation.
 *
 * Note: O1 = Offspring 1, P1 = Parent 1, P2_i = Parent2 allele at position i
 *
 * 1. Select a start and end range. Exclusive.
 * 2. For P1, swap alleles within range into offspring.
 * 3. For P2, get a list of alleles within range that are not in offspring.
 * 4. Add these to offspring following these steps:
 *      - To find a new gene position for P2_i, check the value of O1_i.
 *      - Find the location of 01_i in P2. Call this position j.
 *      - If position j is open in O1, put P2_i at O1_j.
 *      - If position j is occupied, find the location of 01_j in P2. Call this position k.
 *      - If position k is open in 01, put P2_i at 01_k.
 * 5. For the remaining alleles in P2, just put them in the corresponding positions at O1.
 * 6. Creating O2 is the same as creating O1. The difference is we start with P1.
 */
public class PartiallyMappedCrossover implements ICrossoverOperator {

    private int _numberOfOffspring = 2;

    public int numberOfOffspring() {
        return _numberOfOffspring;
    }

    public List<Genotype> crossover(Genotype genotype1, Genotype genotype2, IRandom random) {

        return null;
    }
}
