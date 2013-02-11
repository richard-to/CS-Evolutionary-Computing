package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/10/13
 */
public class TournamentSelectorTest {
    @Test
    public void testSelectParentsMax() throws Exception {
        int tournamentSize = 3;

        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        Comparator<Pair<Double, Genotype>> fitnessComparator = new FitnessMaximizationComparator();

        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0, 1, 1, 3, 2, 1, 0, 3, 2, 3, 3, 2}));
        MockRandom random = new MockRandom();
        random.setIntegerSequence(sequence);

        TournamentSelector parentSelector = new TournamentSelector(
                tournamentSize, fitnessEvaluator, fitnessComparator, random);

        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1), new Allele(3)}));
            add(new Genotype(new Allele[]{new Allele(1), new Allele(3), new Allele(2)}));
            add(new Genotype(new Allele[]{new Allele(3), new Allele(1), new Allele(2)}));
        }};

        List<Genotype> parentGenotypes = parentSelector.selectParents(genotypes);
        assertEquals(genotypes.get(1), parentGenotypes.get(0));
        assertEquals(genotypes.get(3), parentGenotypes.get(1));
        assertEquals(genotypes.get(3), parentGenotypes.get(2));
        assertEquals(genotypes.get(3), parentGenotypes.get(3));
    }

    @Test
    public void testSelectParentsMin() throws Exception {
        int tournamentSize = 3;

        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        Comparator<Pair<Double, Genotype>> fitnessComparator = new FitnessMinimizationComparator();

        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0, 1, 1, 3, 2, 1, 0, 3, 2, 3, 3, 2}));
        MockRandom random = new MockRandom();
        random.setIntegerSequence(sequence);

        TournamentSelector parentSelector = new TournamentSelector(
                tournamentSize, fitnessEvaluator, fitnessComparator, random);

        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1), new Allele(3)}));
            add(new Genotype(new Allele[]{new Allele(1), new Allele(3), new Allele(2)}));
            add(new Genotype(new Allele[]{new Allele(3), new Allele(1), new Allele(2)}));
        }};

        List<Genotype> parentGenotypes = parentSelector.selectParents(genotypes);
        assertEquals(genotypes.get(0), parentGenotypes.get(0));
        assertEquals(genotypes.get(1), parentGenotypes.get(1));
        assertEquals(genotypes.get(0), parentGenotypes.get(2));
        assertEquals(genotypes.get(2), parentGenotypes.get(3));
    }
}
