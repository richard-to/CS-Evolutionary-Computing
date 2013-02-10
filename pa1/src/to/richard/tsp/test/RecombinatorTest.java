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
    }
}
