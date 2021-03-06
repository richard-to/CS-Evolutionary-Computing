package to.richard.test;

import org.junit.Test;
import to.richard.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

public class GenePoolInitializerTest {

    @Test
    public void testCreateRandomGenotype() throws Exception {
        MockRandom rand = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        sequence.add(21);
        rand.setIntegerSequence(sequence);
        GenePoolInitializer genePoolInitializer = new GenePoolInitializer(31, 4, rand);
        Genotype genotype = genePoolInitializer.createRandomGenotype();
        int[] expectedGenes = new int[]{1, 0, 1, 0, 1};
        assertArrayEquals(expectedGenes, genotype.getGenes());
    }

    @Test
    public void testCreateGenePool() throws Exception {
        MockRandom rand = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>(Arrays.asList(
                new Integer[]{21, 15, 3, 7}));
        rand.setIntegerSequence(sequence);
        GenePoolInitializer genePoolInitializer = new GenePoolInitializer(31, 4, rand);
        GenePool genePool = genePoolInitializer.initializeGenePool();
        assertEquals(new Integer(genePool.getMaxValue()), new Integer(31));
        assertEquals(new Integer(genePool.getPopulationSize()), new Integer(4));
        Genotype genotype = genePool.getGenotype(0);
        int[] expectedGenes = new int[]{1, 0, 1, 0, 1};
        assertArrayEquals(expectedGenes, genotype.getGenes());
    }
}
