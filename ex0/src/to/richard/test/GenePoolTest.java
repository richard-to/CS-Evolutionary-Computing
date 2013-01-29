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

public class GenePoolTest {

    @Test
    public void testGenePool() throws Exception {
        GenePool genePool = new GenePool(32, 4);
        genePool.addGenotype(new Genotype(20, 5))
                .addGenotype(new Genotype(5, 5))
                .addGenotype(new Genotype(2, 5))
                .addGenotype(new Genotype(27, 5));
        Genotype genotype = genePool.getGenotype(0);
        int[] expectedBitArray = new int[]{1, 0, 1, 0, 0};
        assertArrayEquals(expectedBitArray, genotype.getGenes());
    }
}
