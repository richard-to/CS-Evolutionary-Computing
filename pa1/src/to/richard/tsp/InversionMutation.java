package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/7/13
 */

/**
 * Performs inversion mutation on genotypes.
 *
 * Start and end points selected are inclusive.
 */
public class InversionMutation implements IMutationOperator {
    public Genotype mutate(Genotype genotype, IRandom random) {
        int start = random.nextInt(genotype.length());
        int end = random.nextInt(genotype.length());

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        int length = end - start + 1;
        MutableGenotype mutableGenotype = genotype.copyMutable();
        for (int i = 0; i < length; i++) {
            mutableGenotype.setAllele(genotype.getAllele(start + i), end - i);
        }
        return mutableGenotype.copy();
    }
}
