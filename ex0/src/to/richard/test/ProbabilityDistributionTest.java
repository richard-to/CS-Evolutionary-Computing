package to.richard.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import to.richard.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

public class ProbabilityDistributionTest {

    @Test
    public void testProbabilityDistribution() throws Exception {
        MockRandom random = new MockRandom();
        ProbabilityDistribution<Integer> probabilityDistribution =
            new ProbabilityDistribution<Integer>(random);
        probabilityDistribution.add(.2, 20).add(.4, 40)
                .add(.15, 15).add(.25, 25);
        assertEquals(new Integer(20), new Integer(probabilityDistribution.get(.1)));
        assertEquals(new Integer(40), new Integer(probabilityDistribution.get(.35)));
        assertEquals(new Integer(15), new Integer(probabilityDistribution.get(.65)));
        assertEquals(new Integer(25), new Integer(probabilityDistribution.get(.89)));
    }

    @Test
    public void testProbabilityDistributionRand() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>();
        sequence.add(0.1);
        sequence.add(.35);
        sequence.add(.89);
        random.setDoubleSequence(sequence);
        ProbabilityDistribution<Integer> probabilityDistribution =
                new ProbabilityDistribution<Integer>(random);
        probabilityDistribution.add(.2, 20).add(.4, 40)
                .add(.15, 15).add(.25, 25);
        assertEquals(new Integer(20), new Integer(probabilityDistribution.getRandom()));
        assertEquals(new Integer(40), new Integer(probabilityDistribution.getRandom()));
        assertEquals(new Integer(25), new Integer(probabilityDistribution.getRandom()));
    }
}
