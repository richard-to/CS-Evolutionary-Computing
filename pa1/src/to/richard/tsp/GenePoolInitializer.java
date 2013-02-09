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
    private ArrayList<Allele> _orderedAlleles;

    public GenePoolInitializer(int populationSize, CostMatrix costMatrix, IRandom random) {
        _populationSize = populationSize;
        _random = random;
        _orderedAlleles = new ArrayList<Allele>();
        Allele[] alleles = costMatrix.getAlleles();
        for (Allele allele : alleles) {
            _orderedAlleles.add(allele);
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
        List<Allele> allelesCopy = new ArrayList<Allele>(_orderedAlleles);
        Collections.shuffle(allelesCopy, _random.getJavaRandom());
        Allele[] shuffledAlleles = new Allele[allelesCopy.size()];
        for (int i = 0; i < shuffledAlleles.length; i++) {
            shuffledAlleles[i] = allelesCopy.get(i);
        }
        return new Genotype(shuffledAlleles);
    }
}
