package to.richard.tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
        if (genotype1.equals(genotype2)) {
            return Arrays.asList(new Genotype[]{genotype1, genotype2});
        }

        int genotypeLength = genotype1.length();
        ArrayList<Genotype> newOffspring = new ArrayList<Genotype>();
        MutableGenotype offspring1 = new MutableGenotype(genotypeLength);
        MutableGenotype offspring2 = new MutableGenotype(genotypeLength);

        ArrayList<HashSet<Integer>> cycleTable = new ArrayList<HashSet<Integer>>();
        HashSet<Integer> currentCycle = null;
        HashSet<Integer> visitedGenePositions = new HashSet<Integer>();
        Allele startAllele = null;
        Allele p1Allele = null;
        Allele p2Allele = null;
        int genePositionCount = 0;
        int nextPosition = 0;
        boolean crossCycle = false;

        /**
         * Find all cycles
         */
        for (int i = 0; i < genotypeLength; i++) {
            if (!visitedGenePositions.contains(i)) {
                currentCycle = new HashSet<Integer>();
                currentCycle.add(i);
                startAllele = genotype1.getAllele(i);
                p2Allele = genotype2.getAllele(i);
                genePositionCount = 0;
                while (!startAllele.equals(p2Allele) && genePositionCount < genotypeLength) {
                    genePositionCount++;
                    nextPosition = genotype1.findAllele(p2Allele);
                    visitedGenePositions.add(nextPosition);
                    currentCycle.add(nextPosition);
                    p2Allele = genotype2.getAllele(nextPosition);
                }

                if (genePositionCount >= genotypeLength) {
                    throw new Errors.CrossoverFailed("Could not find cycle to complete cycle crossover");
                }

                cycleTable.add(currentCycle);
            }
        }

        /**
         * Go through all cycles in cycle table.
         * Pattern goes same - swap - same - swap, etc.
         */
        for (HashSet<Integer> cycle : cycleTable) {
            for (Integer position : cycle) {
                p1Allele = genotype1.getAllele(position);
                p2Allele = genotype2.getAllele(position);
                if (crossCycle) {
                    offspring1.setAllele(p2Allele, position);
                    offspring2.setAllele(p1Allele, position);
                } else {
                    offspring1.setAllele(p1Allele, position);
                    offspring2.setAllele(p2Allele, position);
                }
            }
            crossCycle = (crossCycle) ? false : true;
        }

        newOffspring.add(offspring1);
        newOffspring.add(offspring2);

        return newOffspring;
    }
}
