package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */
public class FitnessProportionateSelectorTest {
    @Test
    public void testSelectParents() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 10}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.93, .55}));
        random.setDoubleSequence(sequence);
        RouletteWheel<Genotype> wheel = new RouletteWheel<Genotype>(random);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);
        FitnessProportionateSelector fps = new FitnessProportionateSelector(
                random, wheel, fitnessEvaluator);

        //82 + 61 = 143
        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            // 0,1 - 1,2 - 2,0
            // 30 + 10 + 42 = 82 (57%)
            add(new Genotype(new int[]{1, 2}));
            // 0,2 - 2,1 - 1,0
            // 10 + 11 + 40 = 61 (43%)
            add(new Genotype(new int[]{2, 1}));
        }};

        List<Genotype> parentGenotypes = fps.selectParents(genotypes);
        assertEquals("21", parentGenotypes.get(0).toString());
        assertEquals("12", parentGenotypes.get(1).toString());
    }
}
