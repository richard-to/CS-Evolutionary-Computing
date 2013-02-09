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
public class MutatorTest {

    @Test
    public void testMutate() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0, 4}));
        random.setIntegerSequence(sequence);
        ArrayList<Double> sequenceDouble = new ArrayList<Double>(Arrays.asList(
                new Double[]{0.1, 0.6}));
        random.setDoubleSequence(sequenceDouble);
        InversionMutation mutationStrategy = new InversionMutation();
        double mutationRate = 0.5;
        Mutator mutator = new Mutator(mutationRate, mutationStrategy, random);

        ArrayList<Genotype> genotypes = new ArrayList<Genotype>() {{
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(2),
                    new Allele(3),new Allele(4),new Allele(5),new Allele(6),new Allele(7)}));
            add(new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(5),
                    new Allele(3),new Allele(4),new Allele(6),new Allele(2),new Allele(7)}));
        }};
        List<Genotype> genotypesNew = mutator.mutate(genotypes);
        assertEquals("43210567", genotypesNew.get(0).toString());
        assertEquals("01534627", genotypesNew.get(1).toString());
    }

    @Test
    public void testInversionMutation() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0, 4, 0, 0, 3, 1}));
        random.setIntegerSequence(sequence);
        Genotype genotype = new Genotype(new Allele[]{new Allele(0),new Allele(1),new Allele(2),
                new Allele(3),new Allele(4),new Allele(5),new Allele(6),new Allele(7)});
        InversionMutation inversion = new InversionMutation();

        Genotype newGenotype = inversion.mutate(genotype, random);
        assertEquals("43210567", newGenotype.toString());

        newGenotype = inversion.mutate(genotype, random);
        assertEquals("01234567", newGenotype.toString());

        newGenotype = inversion.mutate(genotype, random);
        assertEquals("03214567", newGenotype.toString());
    }
}
