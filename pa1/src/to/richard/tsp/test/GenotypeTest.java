package to.richard.tsp.test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.Errors;
import to.richard.tsp.Genotype;
import to.richard.tsp.MutableGenotype;

/**
 * Author: Richard To
 * Date: 2/5/13
 */
public class GenotypeTest {

    @Test
    public void testGetAllele() throws Exception {
        int[] alleles = {0, 1, 2, 3, 4};
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

    @Test
    public void testSetAllele() throws Exception {
        MutableGenotype mutableGenotype = new MutableGenotype(3);
        mutableGenotype.setAllele(10, 1);
        assertEquals(0, mutableGenotype.getAllele(0));
        assertEquals(10, mutableGenotype.getAllele(1));
        mutableGenotype.setAllele(20, 1);
        assertEquals(20, mutableGenotype.getAllele(1));
    }

    @Test
    public void testCopy() throws Exception {
        MutableGenotype mutableGenotype =
            new MutableGenotype(new int[]{0, 1, 2, 3, 4});
        Genotype genotype = mutableGenotype.copy();
        mutableGenotype.setAllele(10, 1);
        assertEquals(1, genotype.getAllele(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsNegative() {
        Genotype genotype = new Genotype(new int[]{0, 1, 2, 3, 4});
        genotype.getAllele(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsOver() {
        Genotype genotype = new Genotype(new int[]{0, 1, 2, 3, 4});
        genotype.getAllele(20);
    }

    @Test(expected = Errors.AllelesDoNotMatchGenes.class)
    public void testAllelesDoNotMatchGenes() {
        MutableGenotype genotype = new MutableGenotype(new int[]{0, 1, 2, 3, 4});
        genotype.setAlleles(new int[]{2,2});
    }
}