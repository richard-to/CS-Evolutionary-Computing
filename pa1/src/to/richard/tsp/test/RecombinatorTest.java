package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Richard To
 * Date: 2/9/13
 */
public class RecombinatorTest {
    @Test
    public void testRecombinePMX() throws Exception {
        double Pc = .9;
        PartiallyMappedCrossover pmx = new PartiallyMappedCrossover();
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.6, .94}));
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{1,5}));
        random.setDoubleSequence(sequence);
        random.setIntegerSequence(sequenceInt);
        Recombinator recombinator = new Recombinator(Pc, pmx, random);

        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(2),
                    new Allele(3),new Allele(4),new Allele(5),new Allele(6)}));
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(5),
                    new Allele(3),new Allele(4),new Allele(6),new Allele(2)}));
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(2),
                    new Allele(3),new Allele(4),new Allele(5),new Allele(6)}));
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(5),
                    new Allele(3),new Allele(4),new Allele(6),new Allele(2)}));
        }};
        List<Genotype> newGenotypes = recombinator.recombine(genotypes);

        // Parents
        // P1 - 0123456
        // P2 - 0153462
        // Offspring
        // O1 - 0123465
        // 02 - 0153426
        assertEquals("0-1-2-3-4-6-5", newGenotypes.get(0).toString());
        assertEquals("0-1-5-3-4-2-6", newGenotypes.get(1).toString());
        assertEquals("0-1-2-3-4-5-6", newGenotypes.get(2).toString());
        assertEquals("0-1-5-3-4-6-2", newGenotypes.get(3).toString());
    }

    @Test
    public void testRecombinePMXPositionClash() throws Exception {
        double Pc = .9;
        PartiallyMappedCrossover pmx = new PartiallyMappedCrossover();
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.6}));
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{1,5}));
        random.setDoubleSequence(sequence);
        random.setIntegerSequence(sequenceInt);
        Recombinator recombinator = new Recombinator(Pc, pmx, random);

        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(2),
                    new Allele(3),new Allele(4),new Allele(5),new Allele(6)}));
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(5),
                    new Allele(3),new Allele(2),new Allele(6),new Allele(4)}));
        }};
        List<Genotype> newGenotypes = recombinator.recombine(genotypes);

        // Parents
        // P1 - 0123456
        // P2 - 0153264
        // Offspring
        // O1 - 0123465
        // 02 - 0153246
        assertEquals("0-1-2-3-4-6-5", newGenotypes.get(0).toString());
        assertEquals("0-1-5-3-2-4-6", newGenotypes.get(1).toString());
    }

    @Test
    public void testRecombinePMXSame() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{1,5}));
        random.setIntegerSequence(sequenceInt);

        PartiallyMappedCrossover pmx = new PartiallyMappedCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        List<Genotype> newGenotypes = pmx.crossover(g1, g2, random);

        assertEquals("1-2-3-4-5-6-7-8-9", newGenotypes.get(0).toString());
        assertEquals("1-2-3-4-5-6-7-8-9", newGenotypes.get(1).toString());
    }

    @Test
    public void testRecombinePMXBookExample() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{2,7}));
        random.setIntegerSequence(sequenceInt);

        PartiallyMappedCrossover pmx = new PartiallyMappedCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                new Allele(8),new Allele(2),new Allele(6),new Allele(5),new Allele(1),new Allele(4)});
        List<Genotype> newGenotypes = pmx.crossover(g1, g2, random);

        assertEquals("9-3-2-4-5-6-7-1-8", newGenotypes.get(0).toString());
    }

    @Test
    public void testRecombineOXBookExample() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{2,7}));
        random.setIntegerSequence(sequenceInt);

        OrderCrossover crossover = new OrderCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                new Allele(8),new Allele(2),new Allele(6),new Allele(5),new Allele(1),new Allele(4)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        assertEquals("3-8-2-4-5-6-7-1-9", newGenotypes.get(0).toString());
    }

    @Test
    public void testRecombineOX() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{3,4}));
        random.setIntegerSequence(sequenceInt);

        OrderCrossover crossover = new OrderCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                new Allele(8),new Allele(2),new Allele(6),new Allele(5),new Allele(1),new Allele(4)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        assertEquals("9-3-7-8-2-6-5-1-4", newGenotypes.get(0).toString());
    }

    @Test
    public void testRecombineOX2() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{4,8}));
        random.setIntegerSequence(sequenceInt);

        OrderCrossover crossover = new OrderCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                new Allele(8),new Allele(2),new Allele(4),new Allele(5),new Allele(1),new Allele(6)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        assertEquals("3-2-4-5-1-6-7-8-9", newGenotypes.get(0).toString());
    }

    @Test
    public void testRecombineCXBookExample() throws Exception {

        MockRandom random = new MockRandom();
        CycleCrossover crossover = new CycleCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                new Allele(8),new Allele(2),new Allele(6),new Allele(5),new Allele(1),new Allele(4)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        assertEquals("1-3-7-4-2-6-5-8-9", newGenotypes.get(0).toString());
        assertEquals("9-2-3-8-5-6-7-1-4", newGenotypes.get(1).toString());
    }

    @Test
    public void testRecombineCXSame() throws Exception {

        MockRandom random = new MockRandom();
        CycleCrossover crossover = new CycleCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        assertEquals("1-2-3-4-5-6-7-8-9", newGenotypes.get(0).toString());
        assertEquals("1-2-3-4-5-6-7-8-9", newGenotypes.get(1).toString());
    }

    @Test
    public void testRecombineCX() throws Exception {

        MockRandom random = new MockRandom();
        CycleCrossover crossover = new CycleCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(5),new Allele(2),new Allele(3),
                new Allele(8),new Allele(1),new Allele(9),new Allele(7),new Allele(4),new Allele(6)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        // Parents
        // 123456789
        // 523819746
        // Offspring
        // X___X____
        // 0, 4
        // XX__X____ CROSS
        // 1
        // XXX_X____
        // 2
        // XXXXX__X_ CROSS
        // 3,7
        // XXXXXX_XX
        // 5, 8
        // 6         CROSS
        assertEquals("1-2-3-8-5-6-7-4-9", newGenotypes.get(0).toString());
        assertEquals("5-2-3-4-1-9-7-8-6", newGenotypes.get(1).toString());
    }

    @Test
    public void testRecombineEXBookExample() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0,0,1}));
        random.setIntegerSequence(sequenceInt);
        EdgeCrossover crossover = new EdgeCrossover();

        Genotype g1 = new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)});
        Genotype g2 = new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                new Allele(8),new Allele(2),new Allele(6),new Allele(5),new Allele(1),new Allele(4)});
        List<Genotype> newGenotypes = crossover.crossover(g1, g2, random);

        assertEquals("1-5-6-2-8-7-3-9-4", newGenotypes.get(0).toString());
    }

    @Test
    public void testRecombineEXwithRecombinator() throws Exception {
        double Pc = .3;
        EdgeCrossover ex = new EdgeCrossover();
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.5, .2, .94, .31}));
        ArrayList<Integer> sequenceInt = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0,0,1}));
        random.setDoubleSequence(sequence);
        random.setIntegerSequence(sequenceInt);
        Recombinator recombinator = new Recombinator(Pc, ex, random);

        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            add(new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                    new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)}));
            add(new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                    new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)}));
            add(new Genotype(new Allele[]{new Allele(9),new Allele(3),new Allele(7),
                    new Allele(8),new Allele(2),new Allele(6),new Allele(5),new Allele(1),new Allele(4)}));
            add(new Genotype(new Allele[]{new Allele(1),new Allele(2),new Allele(3),
                    new Allele(4),new Allele(5),new Allele(6),new Allele(7),new Allele(8),new Allele(9)}));
        }};
        List<Genotype> newGenotypes = recombinator.recombine(genotypes);

        assertEquals("1-2-3-4-5-6-7-8-9", newGenotypes.get(0).toString());
        assertEquals("1-5-6-2-8-7-3-9-4", newGenotypes.get(1).toString());
        assertEquals("9-3-7-8-2-6-5-1-4", newGenotypes.get(2).toString());
        assertEquals("1-2-3-4-5-6-7-8-9", newGenotypes.get(3).toString());
    }
}
