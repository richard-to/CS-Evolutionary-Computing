package to.richard.tsp.test;

import org.junit.Test;
import to.richard.tsp.MockRandom;
import to.richard.tsp.Pair;
import to.richard.tsp.StochasticUniversalSampler;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 2/10/13
 */
public class StochasticUniversalSamplerTest {
    @Test
    public void testSample() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>();
        sequence.add(0.18);
        random.setDoubleSequence(sequence);

        ArrayList<Pair<Double, Integer>> valueMap = new ArrayList<Pair<Double, Integer>>() {{
            add(new Pair<Double,Integer>(20.0, 20));
            add(new Pair<Double,Integer>(40.0, 40));
            add(new Pair<Double,Integer>(15.0, 15));
            add(new Pair<Double,Integer>(25.0, 25));
        }};

        StochasticUniversalSampler<Integer> sampler =
                new StochasticUniversalSampler<Integer>(random);
        ArrayList<Integer> sampledIntegers = sampler.sample(valueMap, 3);

        assertEquals(new Integer(20), sampledIntegers.get(0));
        assertEquals(new Integer(40), sampledIntegers.get(1));
        assertEquals(new Integer(15), sampledIntegers.get(2));
    }
}
