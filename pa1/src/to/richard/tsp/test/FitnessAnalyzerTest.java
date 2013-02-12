package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: Richard To
 * Date: 2/11/13
 */
public class FitnessAnalyzerTest {

    @Test
    public void testAnalyze() throws Exception {
        Comparator<Pair<Double, Genotype>> comparator = new FitnessMinimizationComparator();
        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);
        FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer(fitnessEvaluator, comparator);
        ArrayList<Genotype> parents = new ArrayList<Genotype>() {{
            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 233 = 701 = 118 = .25
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1), new Allele(3)}));

            // Fitness 283 = 651 = 68 = .14
            add(new Genotype(new Allele[]{new Allele(1), new Allele(3), new Allele(2)}));

            // Fitness 351 = 583 = 0
            add(new Genotype(new Allele[]{new Allele(3), new Allele(1), new Allele(2)}));
        }};
        FitnessResult result = fitnessAnalyzer.analyze(parents);
        assertEquals(67, result.getBest(), 0.1);
        assertEquals(934, result.getSum(), 0.1);
        assertEquals(233.5, result.getAvg(), 0.1);
    }
}
