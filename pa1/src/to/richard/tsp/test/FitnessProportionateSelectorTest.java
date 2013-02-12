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
                fitnessEvaluator, wheel, random);

        //82 + 61 = 143
        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            // 0,1 - 1,2 - 2,0
            // 30 + 10 + 42 = 82 (57%)
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2)}));
            // 0,2 - 2,1 - 1,0
            // 10 + 11 + 40 = 61 (43%)
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1)}));
        }};

        List<Genotype> parentGenotypes = fps.selectParents(genotypes);
        assertEquals("2-1", parentGenotypes.get(0).toString());
        assertEquals("1-2", parentGenotypes.get(1).toString());
    }

    @Test
    public void testFPSMinimization() throws Exception {
        FPSMinimization fpsMinimization = new FPSMinimization();
        ArrayList<Pair<Double, Genotype>> pairs = new ArrayList<Pair<Double, Genotype>>() {{
            add(new Pair<Double, Genotype>(10.0, new MutableGenotype(3)));
            add(new Pair<Double, Genotype>(50.0, new MutableGenotype(3)));
            add(new Pair<Double, Genotype>(90.0, new MutableGenotype(3)));
        }};

        List<Pair<Double, Genotype>> newPairs = fpsMinimization.transform(pairs);
        assertEquals(140.0, newPairs.get(0).getFirstValue(), 0.1);
        assertEquals(100.0, newPairs.get(1).getFirstValue(), 0.1);
        assertEquals(60.0, newPairs.get(2).getFirstValue(), 0.1);
    }

    @Test
    public void testFPSLinearNormalization() throws Exception {
        FPSLinearNormalization fpsTransform = new FPSLinearNormalization(new FitnessMinimizationComparator());
        ArrayList<Pair<Double, Genotype>> pairs = new ArrayList<Pair<Double, Genotype>>() {{
            add(new Pair<Double, Genotype>(90.0, new MutableGenotype(new Allele[]{new Allele(1)})));
            add(new Pair<Double, Genotype>(10.0, new MutableGenotype(new Allele[]{new Allele(2)})));
            add(new Pair<Double, Genotype>(50.0, new MutableGenotype(new Allele[]{new Allele(3)})));
            add(new Pair<Double, Genotype>(50.0, new MutableGenotype(new Allele[]{new Allele(4)})));
        }};

        List<Pair<Double, Genotype>> newPairs = fpsTransform.transform(pairs);
        assertEquals(4, newPairs.get(0).getFirstValue(), 0.1);
        assertEquals(2.5, newPairs.get(1).getFirstValue(), 0.1);
        assertEquals(2.5, newPairs.get(2).getFirstValue(), 0.1);
        assertEquals(1, newPairs.get(3).getFirstValue(), 0.1);

        assertEquals("2", newPairs.get(0).getSecondValue().toString());
        assertEquals("1", newPairs.get(3).getSecondValue().toString());

    }

    @Test
    public void testFPSWindowing() throws Exception {
        FPSWindowing fpsTransform = new FPSWindowing();
        ArrayList<Pair<Double, Genotype>> pairs = new ArrayList<Pair<Double, Genotype>>() {{
            add(new Pair<Double, Genotype>(90.0, new MutableGenotype(new Allele[]{new Allele(1)})));
            add(new Pair<Double, Genotype>(10.0, new MutableGenotype(new Allele[]{new Allele(2)})));
            add(new Pair<Double, Genotype>(50.0, new MutableGenotype(new Allele[]{new Allele(3)})));
            add(new Pair<Double, Genotype>(50.0, new MutableGenotype(new Allele[]{new Allele(4)})));
        }};

        List<Pair<Double, Genotype>> newPairs = fpsTransform.transform(pairs);
        assertEquals(80.0, newPairs.get(0).getFirstValue(), 0.1);
        assertEquals(0.0, newPairs.get(1).getFirstValue(), 0.1);
        assertEquals(40.0, newPairs.get(2).getFirstValue(), 0.1);
        assertEquals(40.0, newPairs.get(3).getFirstValue(), 0.1);
    }

    @Test
    public void testFPSExponential() throws Exception {
        FPSExponential fpsTransform = new FPSExponential();
        ArrayList<Pair<Double, Genotype>> pairs = new ArrayList<Pair<Double, Genotype>>() {{
            add(new Pair<Double, Genotype>(16.0, new MutableGenotype(new Allele[]{new Allele(1)})));
            add(new Pair<Double, Genotype>(9.0, new MutableGenotype(new Allele[]{new Allele(2)})));
            add(new Pair<Double, Genotype>(25.0, new MutableGenotype(new Allele[]{new Allele(3)})));
        }};

        List<Pair<Double, Genotype>> newPairs = fpsTransform.transform(pairs);
        assertEquals(5.0, newPairs.get(0).getFirstValue(), 0.1);
        assertEquals(4.0, newPairs.get(1).getFirstValue(), 0.1);
        assertEquals(6.0, newPairs.get(2).getFirstValue(), 0.1);
    }

    @Test
    public void testFPSWithWindowingAndMinimization() throws Exception {
        int tournamentSize = 3;

        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        ArrayList<IFPSTransform> transforms = new ArrayList<IFPSTransform>() {{
            add(new FPSMinimization());
            add(new FPSWindowing());
        }};

        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.93, .70, .2, .59}));
        random.setDoubleSequence(sequence);
        RouletteWheel<Genotype> wheel = new RouletteWheel<Genotype>(random);

        FitnessProportionateSelector parentSelector = new FitnessProportionateSelector(
                fitnessEvaluator, wheel, random, transforms);

        // 934 // 470
        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 233 = 701 = 118 = .25
            add(new Genotype(new Allele[]{new Allele(2), new Allele(1), new Allele(3)}));

            // Fitness 283 = 651 = 68 = .14
            add(new Genotype(new Allele[]{new Allele(1), new Allele(3), new Allele(2)}));

            // Fitness 351 = 583 = 0
            add(new Genotype(new Allele[]{new Allele(3), new Allele(1), new Allele(2)}));
        }};

        List<Genotype> parentGenotypes = parentSelector.selectParents(genotypes);
        assertEquals(genotypes.get(2), parentGenotypes.get(0));
        assertEquals(genotypes.get(1), parentGenotypes.get(1));
        assertEquals(genotypes.get(0), parentGenotypes.get(2));
        assertEquals(genotypes.get(0), parentGenotypes.get(3));
    }


    @Test
    public void testFPSWithWindowingAndMinimizationEqualFitness() throws Exception {
        int tournamentSize = 3;

        int[][] costMatrixArray = {{20, 30, 10, 75}, {40, 21, 23, 200}, {42, 11, 101, 2}, {12, 211, 11, 56}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);

        FitnessEvaluator fitnessEvaluator = new FitnessEvaluator(costMatrix);

        ArrayList<IFPSTransform> transforms = new ArrayList<IFPSTransform>() {{
            add(new FPSMinimization());
            add(new FPSWindowing());
        }};

        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.93, .70, .2, .59}));
        random.setDoubleSequence(sequence);
        RouletteWheel<Genotype> wheel = new RouletteWheel<Genotype>(random);

        FitnessProportionateSelector parentSelector = new FitnessProportionateSelector(
                fitnessEvaluator, wheel, random, transforms);

        // 934 // 470
        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));

            // Fitness 67  = 867 = 284 = .604
            add(new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(3)}));
        }};

        List<Genotype> parentGenotypes = parentSelector.selectParents(genotypes);
        assertEquals(genotypes.get(0), parentGenotypes.get(0));
        assertEquals(genotypes.get(1), parentGenotypes.get(1));
        assertEquals(genotypes.get(2), parentGenotypes.get(2));
        assertEquals(genotypes.get(3), parentGenotypes.get(3));
    }
}
