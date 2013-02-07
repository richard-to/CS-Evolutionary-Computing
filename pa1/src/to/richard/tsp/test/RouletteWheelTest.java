package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import to.richard.tsp.*;

/**
 * Author: Richard To
 * Date: 2/6/13
 */
public class RouletteWheelTest {
    @Test
    public void testSampleOne() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>();
        sequence.add(0.1);
        sequence.add(.35);
        sequence.add(.89);
        random.setDoubleSequence(sequence);

        ArrayList<Pair<Double, Integer>> valueMap = new ArrayList<Pair<Double, Integer>>() {{
            add(new Pair<Double,Integer>(20.0, 20));
            add(new Pair<Double,Integer>(40.0, 40));
            add(new Pair<Double,Integer>(15.0, 15));
            add(new Pair<Double,Integer>(25.0, 25));
        }};

        RouletteWheel<Integer> sampler = new RouletteWheel<Integer>(random);

        assertEquals(new Integer(20), new Integer(sampler.sampleOne(valueMap)));
        assertEquals(new Integer(40), new Integer(sampler.sampleOne(valueMap)));
        assertEquals(new Integer(25), new Integer(sampler.sampleOne(valueMap)));
    }

    @Test
    public void testSample() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>();
        sequence.add(0.1);
        sequence.add(.35);
        sequence.add(.89);
        random.setDoubleSequence(sequence);

        ArrayList<Pair<Double, Integer>> valueMap = new ArrayList<Pair<Double, Integer>>() {{
            add(new Pair<Double,Integer>(20.0, 20));
            add(new Pair<Double,Integer>(40.0, 40));
            add(new Pair<Double,Integer>(15.0, 15));
            add(new Pair<Double,Integer>(25.0, 25));
        }};

        RouletteWheel<Integer> sampler =
                new RouletteWheel<Integer>(random);
        ArrayList<Integer> sampledIntegers = sampler.sample(valueMap, 3);

        assertEquals(new Integer(20), sampledIntegers.get(0));
        assertEquals(new Integer(40), sampledIntegers.get(1));
        assertEquals(new Integer(25), sampledIntegers.get(2));
    }
}
