package to.richard.test;

import org.junit.Test;

import to.richard.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 1/29/13
 */
public class MatingPoolTest {

    @Test
    public void testRecombine() throws Exception {

        MockRandom random = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        sequence.add(2);
        sequence.add(1);

        random.setIntegerSequence(sequence);
        MatingPool matingPool = new MatingPool(random);

        ArrayList<GenotypeParents> genotypeParentsList = new ArrayList<GenotypeParents>();
        genotypeParentsList.add(new GenotypeParents(
            new Genotype(20, 5), // 1 0 1 0 0
            new Genotype(7, 5)   // 0 0 1 1 1
        ));

        List<Genotype> genotypeList = matingPool.recombine(genotypeParentsList);
        assertEquals(21, genotypeList.get(0).getValue());
        assertEquals(441, genotypeList.get(0).getFitness());
        assertEquals(6, genotypeList.get(1).getValue());
        assertEquals(36, genotypeList.get(1).getFitness());

        genotypeList = matingPool.recombine(genotypeParentsList);
        assertEquals(23, genotypeList.get(0).getValue());
        assertEquals(529, genotypeList.get(0).getFitness());
        assertEquals(4, genotypeList.get(1).getValue());
        assertEquals(16, genotypeList.get(1).getFitness());
    }
}
