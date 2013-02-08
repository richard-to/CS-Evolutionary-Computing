package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Richard To
 * Date: 2/7/13
 */
public class InversionMutationTest {
    @Test
    public void testMutate() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{0, 4, 0, 0, 3, 1}));
        random.setIntegerSequence(sequence);
        Genotype genotype = new Genotype(new int[]{0,1,2,3,4,5,6,7});
        InversionMutation inversion = new InversionMutation();

        Genotype newGenotype = inversion.mutate(genotype, random);
        assertEquals("43210567", newGenotype.toString());

        newGenotype = inversion.mutate(genotype, random);
        assertEquals("01234567", newGenotype.toString());

        newGenotype = inversion.mutate(genotype, random);
        assertEquals("03214567", newGenotype.toString());
    }
}
