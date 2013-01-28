package to.richard.test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import to.richard.*;

import java.util.Random;

/**
 * Author: Richard To
 * Date: 1/28/13
 */

public class XSquaredGenePoolTest {

    @Test
    public void testCreateRandomGenotype() throws Exception {
        Random rand = new Random(0);
        XSquaredGenotype genotype = XSquaredGenePool.createRandomGenotype(31, rand);
        int[] expectedGenes = new int[]{0, 0, 0, 1, 0};
        assertArrayEquals(genotype.getGenes(), expectedGenes);
    }
}
