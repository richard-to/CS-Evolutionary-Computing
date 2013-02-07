package to.richard.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Author: Richard To
 * Date: 2/6/13
 */

/**
 * Factory to randomly generate a list of genotypes for generation 0.
 * Duplicates are allowed.
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

    public List<Genotype> initializeGenePool() {
        ArrayList<Genotype> genotypes = new ArrayList<Genotype>();
        for (int i = 0; i < _populationSize; i++) {
            genotypes.add(createRandomGenotype());
        }
        return genotypes;
    }

    /**
     * Create a random genotype by shuffling the order of alleles.
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
