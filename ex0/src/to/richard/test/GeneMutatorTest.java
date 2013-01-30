package to.richard.test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import to.richard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

public class GeneMutatorTest {

    @Test
    public void testMutation() throws Exception {

        MockRandom mockRandom = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>(Arrays.asList(
                new Double[]{.4, .78, .005, .25, .65, .14, .78, .15, .99, .001}));
        mockRandom.setDoubleSequence(sequence);
        ArrayList<Genotype> genotypeList = new ArrayList<Genotype>();
        genotypeList.add(new Genotype(3, 5));
        genotypeList.add(new Genotype(16, 5));

        GeneMutator geneMutator = new GeneMutator(mockRandom, .1);
        List<Genotype> mutatedList = geneMutator.mutate(genotypeList);

        assertEquals(7, mutatedList.get(0).getValue());
        assertEquals(17, mutatedList.get(1).getValue());
    }
}
