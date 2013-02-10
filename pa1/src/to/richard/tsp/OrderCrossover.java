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

    public List<Genotype> crossover(Genotype parent1, Genotype parent2, IRandom random) {

        if (parent1.equals(parent2)) {
            return Arrays.asList(new Genotype[]{parent1, parent2});
        }

        int genotypeLength = parent1.length();

        ArrayList<Genotype> newOffspring = new ArrayList<Genotype>();

        /**
         * Select two crossover boundaries. Exclusive.
         */
        int start = random.nextInt(genotypeLength);
        int end = random.nextInt(genotypeLength);
        int temp;
        if (start > end) {
            temp = start;
            start = end;
            end = temp;
        }

        newOffspring.add(crossover(parent1, parent2, start, end));
        newOffspring.add(crossover(parent2, parent1, start, end));
        return newOffspring;
    }

    private Genotype crossover(Genotype parent1, Genotype parent2, int start, int end) {
        int genotypeLength = parent1.length();

        MutableGenotype offspring = new MutableGenotype(genotypeLength);
        HashSet<Allele> inheritedAlleles = new HashSet<Allele>();

        int offspringGenePosition = end;
        int p2GenePosition = end;
        int genePositionCount = 0;
        Allele currentAllele = null;

        /**
         * Add alleles between selected crossover boundaries to offspring.
         */
        for (int i = start + 1; i < end; i++) {
            inheritedAlleles.add(parent1.getAllele(i));
            offspring.setAllele(parent1.getAllele(i), i);
        }

        /**
         * Cycle through p2 from crossover point 2 (wrap to beginning). If not in offspring,
         * add to gene position starting from end.
         *
         * If allele is added, the gene position is incremented. Wraps to beginning
         * once it gets to the end.
         */
        while (genePositionCount < genotypeLength) {
            currentAllele = parent2.getAllele(p2GenePosition);
            if (!inheritedAlleles.contains(currentAllele)) {
                offspring.setAllele(currentAllele, offspringGenePosition);
                offspringGenePosition = (offspringGenePosition + 1) % genotypeLength;
            }
            genePositionCount++;
            p2GenePosition = (p2GenePosition + 1) % genotypeLength;
        }

        return offspring;
    }
}
