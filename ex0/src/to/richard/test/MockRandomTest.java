package to.richard.test;

import org.junit.Test;
import to.richard.IRandom;
import to.richard.MockRandom;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 1/29/13
 */
public class MockRandomTest {
    @Test
    public void testNextInt() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        sequence.add(1);
        sequence.add(2);
        sequence.add(3);
        random.setIntegerSequence(sequence);
        assertEquals(1, random.nextInt(1));
        assertEquals(2, random.nextInt(1));
        assertEquals(3, random.nextInt(1));
    }

    @Test
    public void testNextDouble() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>();
        sequence.add(1.1);
        sequence.add(2.2);
        sequence.add(3.2);
        random.setDoubleSequence(sequence);
        assertEquals(1.1, random.nextDouble());
        assertEquals(2.2, random.nextDouble());
        assertEquals(3.2, random.nextDouble());
    }
}
