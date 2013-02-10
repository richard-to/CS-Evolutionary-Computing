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
        assertEquals("0123465", newGenotypes.get(0).toString());
        assertEquals("0153426", newGenotypes.get(1).toString());
        assertEquals("0123456", newGenotypes.get(2).toString());
        assertEquals("0153462", newGenotypes.get(3).toString());
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
        assertEquals("0123465", newGenotypes.get(0).toString());
        assertEquals("0153246", newGenotypes.get(1).toString());
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

        assertEquals("123456789", newGenotypes.get(0).toString());
        assertEquals("123456789", newGenotypes.get(1).toString());
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

        assertEquals("932456718", newGenotypes.get(0).toString());
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

        assertEquals("382456719", newGenotypes.get(0).toString());
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

        assertEquals("937826514", newGenotypes.get(0).toString());
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

        assertEquals("324516789", newGenotypes.get(0).toString());
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

        assertEquals("137426589", newGenotypes.get(0).toString());
        assertEquals("923856714", newGenotypes.get(1).toString());
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

        assertEquals("123456789", newGenotypes.get(0).toString());
        assertEquals("123456789", newGenotypes.get(1).toString());
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
        assertEquals("123856749", newGenotypes.get(0).toString());
        assertEquals("523419786", newGenotypes.get(1).toString());
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

        assertEquals("156287394", newGenotypes.get(0).toString());
    }
}
