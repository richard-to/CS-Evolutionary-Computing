package to.richard.tsp.test;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/11/13
 */
public class SurvivalSelectorTest {

    @Test
    public void testAgedBased() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        ArrayList<Genotype> parents = new ArrayList<Genotype>() {{
            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 233 = 701 = 118 = .25
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1), new Allele(3)}));
        }};

        ArrayList<Genotype> offspring = new ArrayList<Genotype>() {{
            // Fitness 283 = 651 = 68 = .14
            add(new Genotype(new Allele[]{new Allele(1), new Allele(3), new Allele(2)}));

            // Fitness 351 = 583 = 0
            add(new Genotype(new Allele[]{new Allele(3), new Allele(1), new Allele(2)}));
        }};

        ISurvivorSelector survivorSelector = new AgeBasedSurvivorSelector();
        List<Genotype> nextGeneration = survivorSelector.replace(parents, offspring);

        assertEquals(offspring.get(0), nextGeneration.get(0));
        assertEquals(offspring.get(1), nextGeneration.get(1));
    }

    @Test
    public void testElitism() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        FitnessMinimizationComparator comparator = new FitnessMinimizationComparator();
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        ArrayList<Genotype> parents = new ArrayList<Genotype>() {{
            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 233 = 701 = 118 = .25
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1), new Allele(3)}));
        }};

        ArrayList<Genotype> offspring = new ArrayList<Genotype>() {{
            // Fitness 283 = 651 = 68 = .14
            add(new Genotype(new Allele[]{new Allele(1), new Allele(3), new Allele(2)}));

            // Fitness 351 = 583 = 0
            add(new Genotype(new Allele[]{new Allele(3), new Allele(1), new Allele(2)}));
        }};

        ISurvivorSelector survivorSelector = new ElitismSurvivorSelection(fitnessEvaluator, comparator);
        List<Genotype> nextGeneration = survivorSelector.replace(parents, offspring);

        assertEquals(parents.get(0), nextGeneration.get(0));
        assertEquals(offspring.get(0), nextGeneration.get(1));
    }
}
