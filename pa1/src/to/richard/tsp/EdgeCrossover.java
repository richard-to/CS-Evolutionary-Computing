package to.richard.tsp;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<Genotype> crossover(Genotype parent1, Genotype parent2, IRandom random) {
        int genotypeLength = parent1.length();

        MutableGenotype offspring = new MutableGenotype(genotypeLength);
        ArrayList<Genotype> newOffspring = new ArrayList<Genotype>();

        Genotype[] parents = new Genotype[]{parent1, parent2};

        ArrayList<Allele> remainingAlleles = new ArrayList<Allele>();

        HashMap<Allele, HashMap<Allele, Integer>> edgeTable = new HashMap<Allele, HashMap<Allele, Integer>>();
        HashMap<Allele, Integer> currentEdgeMap = null;

        ArrayList<Allele> shortestEdgeLists = new ArrayList<Allele>();

        Allele currentAllele = null;
        Allele edgeAllele = null;

        int[] edgeIndexes = new int[parents.length];

        int edgeCount = 0;
        int alleleIndex = 0;
        int genotypeCount = 0;

        /**
         * Build edge table
         */
        for (Allele allele : parent1) {
            remainingAlleles.add(allele);
            edgeTable.put(allele, new HashMap<Allele, Integer>());
        }

        for (Genotype parent : parents) {
            for (int i = 0; i < genotypeLength; i++) {
                currentAllele = parent.getAllele(i);
                currentEdgeMap = edgeTable.get(currentAllele);
                edgeIndexes[0] = (i == 0) ? genotypeLength - 1 : i - 1;
                edgeIndexes[1] = (i + 1) % genotypeLength;

                for (int edgeIndex : edgeIndexes) {
                    edgeAllele = parent.getAllele(edgeIndex);
                    if (!currentEdgeMap.containsKey(edgeAllele)) {
                        currentEdgeMap.put(edgeAllele, 1);
                    } else {
                        currentEdgeMap.put(edgeAllele, currentEdgeMap.get(edgeAllele) + 1);
                    }
                }
            }
        }

        /**
         * Select random allele to start
         */
        alleleIndex = random.nextInt(remainingAlleles.size());
        currentAllele = remainingAlleles.remove(alleleIndex);
        offspring.setAllele(currentAllele, genotypeCount++);

        /**
         * Remove allele references from edge table
         */
        for (HashMap<Allele, Integer> edgeMap : edgeTable.values()) {
            edgeMap.remove(currentAllele);
        }

        while (remainingAlleles.size() > 0) {

            /**
             * Pick the next allele to add
             */
            currentEdgeMap = edgeTable.remove(currentAllele);

            /**
             * First look for common edge
             */
            currentAllele = null;
            for (Allele alleleEdge : currentEdgeMap.keySet()) {
                edgeCount = currentEdgeMap.get(alleleEdge);
                if (edgeCount > 1) {
                    currentAllele = alleleEdge;
                    break;
                }
            }

            /**
             * If no common edge, then find shortest list
             */
            if (currentAllele == null) {
                shortestEdgeLists.clear();
                edgeCount = 0;

                for (Allele alleleEdge : currentEdgeMap.keySet()) {
                    currentEdgeMap = edgeTable.get(alleleEdge);
                    if (shortestEdgeLists.isEmpty() || currentEdgeMap.size() < edgeCount) {
                        edgeCount = currentEdgeMap.size();
                        shortestEdgeLists.clear();
                        shortestEdgeLists.add(alleleEdge);
                    } else if (currentEdgeMap.size() == edgeCount) {
                        shortestEdgeLists.add(alleleEdge);
                    }
                }

                if (shortestEdgeLists.size() == 1) {
                    currentAllele = shortestEdgeLists.get(0);
                } else if (shortestEdgeLists.size() > 1) {
                    alleleIndex = random.nextInt(shortestEdgeLists.size());
                    currentAllele = shortestEdgeLists.get(alleleIndex);
                }
            }

            /**
             * If allele has no edges, then choose one at random.
             */
            if (currentAllele == null) {
                alleleIndex = random.nextInt(remainingAlleles.size());
                currentAllele = remainingAlleles.get(alleleIndex);
            }

            /**
             * Remove selected allele from remaining alleles list.
             */
            remainingAlleles.remove(currentAllele);
            offspring.setAllele(currentAllele, genotypeCount++);

            /**
             * Remove allele references from edge table
             */
            for (HashMap<Allele, Integer> edge : edgeTable.values()) {
                edge.remove(currentAllele);
            }
        }

        newOffspring.add(offspring);
        return newOffspring;
    }
}
