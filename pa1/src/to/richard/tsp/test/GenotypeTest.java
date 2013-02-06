package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import to.richard.tsp.Genotype;
import to.richard.tsp.MutableGenotype;

/**
 * Author: Richard To
 * Date: 2/5/13
 */
public class GenotypeTest {

    @Test
    public void testGetAllele() throws Exception {
        int[] alleles = new int[]{0, 1, 2, 3, 4};
        Genotype genotype = new Genotype(alleles);
        int value = genotype.getAllele(3);
        value = 5;
        alleles[3] = 10;
        assertEquals(3, genotype.getAllele(3));
    }

    @Test
    public void testLength() throws Exception {
        Genotype genotype = new Genotype(new int[]{0, 1, 2, 3, 4});
        assertEquals(5, genotype.length());
    }

    @Test
    public void testCopyMutable() throws Exception {
        Genotype genotype = new Genotype(new int[]{0, 1, 2, 3, 4});
        MutableGenotype mutableGenotype = genotype.copyMutable();
        assertEquals(3, mutableGenotype.getAllele(3));
        mutableGenotype.setAllele(10, 3);
        assertEquals(3, genotype.getAllele(3));
        assertEquals(10, mutableGenotype.getAllele(3));
    }
}
