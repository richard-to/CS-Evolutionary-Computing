package to.richard;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

/**
 * Mutates genotype by randomly flipping bits (genes/alles).
 */
public class GeneMutator {

    private IRandom _random;
    private double _mutationRate;

    /**
     * Mutation rate should be between 0 and 1.
     *
     * Usually mutation rate is a fairly low number.
     * If the mutation rate is greater than 1, then mutation rate will be 100%.
     * If the rate is less than 0, then it will be 0%.
     *
     * @param random
     * @param mutationRate
     */
    public GeneMutator(IRandom random, double mutationRate) {
        _random = random;
        _mutationRate = mutationRate;
    }

    /**
     * Randomly mutates genes in genetypes
     * @param genotypeList
     * @return List<Genotype>
     */
    public List<Genotype> mutate(List<Genotype> genotypeList) {
        ArrayList<Genotype> mutatedGenotypeList = new ArrayList<Genotype>();
        int[] bitArray = null;

        for (Genotype genotype : genotypeList) {
            bitArray = genotype.getGenes();
            for (int i = 0; i < bitArray.length; i++) {
                if (_random.nextDouble() < _mutationRate) {
                    bitArray[i] = (bitArray[i] == 1) ? 0 : 1;
                }
            }
            mutatedGenotypeList.add(new Genotype(bitArray));
        }
        return mutatedGenotypeList;
    }
}
