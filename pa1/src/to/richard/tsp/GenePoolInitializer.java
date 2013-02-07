package to.richard.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Initializes List of Genotypes for initial gene pool for generation 0.
 */
public class GenePoolInitializer {

    private int _populationSize;
    private IRandom _random;
    private ArrayList<Integer> _orderedAlleles;

    public GenePoolInitializer(int populationSize, CostMatrix costMatrix, IRandom random) {
        _populationSize = populationSize;
        _random = random;
        _orderedAlleles = new ArrayList<Integer>();
        int[] alleles = costMatrix.getAlleles();
        for (int i = 0; i < alleles.length; i++) {
            _orderedAlleles.add(alleles[i]);
        }
    }

    /**
     * Initializes random gene pool.
     *
     * Population size and cost matrix are set in constructor.
     * @return
     */
    public List<Genotype> initializeGenePool() {
        ArrayList<Genotype> genotypes = new ArrayList<Genotype>();
        for (int i = 0; i < _populationSize; i++) {
            genotypes.add(createRandomGenotype());
        }
        return genotypes;
    }

    /**
     * Create a random genotype by shuffling the order of alleles.
     * @return Genotype
     */
    public Genotype createRandomGenotype() {
        ArrayList<Integer> allelesCopy = new ArrayList<Integer>(_orderedAlleles);
        Collections.shuffle(allelesCopy, _random.getJavaRandom());
        int[] shuffledAlleles = new int[allelesCopy.size()];
        for (int i = 0; i < shuffledAlleles.length; i++) {
            shuffledAlleles[i] = allelesCopy.get(i);
        }
        return new Genotype(shuffledAlleles);
    }
}
