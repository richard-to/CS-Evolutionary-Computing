package to.richard.test;

import org.junit.Test;
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
        Random random = new Random(0);
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
        Random random = new Random(0);
        ProbabilityDistribution<Integer> probabilityDistribution =
                new ProbabilityDistribution<Integer>(random);
        probabilityDistribution.add(.2, 20).add(.4, 40)
                .add(.15, 15).add(.25, 25);
        assertEquals(new Integer(15), new Integer(probabilityDistribution.getRandom()));
    }
}
