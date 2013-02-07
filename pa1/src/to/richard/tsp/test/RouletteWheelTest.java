package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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

        LinkedHashMap<Integer, Integer> valueMap = new LinkedHashMap<Integer, Integer>() {{
            put(20, 20);
            put(40, 40);
            put(15, 15);
            put(25, 25);
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

        LinkedHashMap<Integer, Integer> valueMap = new LinkedHashMap<Integer, Integer>() {{
            put(20, 20);
            put(40, 40);
            put(15, 15);
            put(25, 25);
        }};

        RouletteWheel<Integer> sampler =
                new RouletteWheel<Integer>(random);
        ArrayList<Integer> sampledIntegers = sampler.sample(valueMap, 3);

        assertEquals(new Integer(20), sampledIntegers.get(0));
        assertEquals(new Integer(40), sampledIntegers.get(1));
        assertEquals(new Integer(25), sampledIntegers.get(2));
    }
}
