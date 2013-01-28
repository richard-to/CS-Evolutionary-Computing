package to.richard.test;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import to.richard.XSquaredGenotype;
/**
 * Author: Richard To
 * Date: 1/28/13
 */
public class XSquaredGenotypeTest {

    @Test
    public void testConvertIntToBitArray() throws Exception {
        int[] bitArray = XSquaredGenotype.convertIntToBitArray(20, 5);
        int[] expectedBitArray = new int[]{1, 0, 1, 0, 0};
        assertArrayEquals(bitArray, expectedBitArray);
    }

    @Test
    public void testConvertIntToBitArrayOverflow() throws Exception {
        int[] bitArray = XSquaredGenotype.convertIntToBitArray(32, 5);
        int[] expectedBitArray = new int[]{0, 0, 0, 0, 0};
        assertArrayEquals(bitArray, expectedBitArray);
    }

    @Test
    public void testProperties() throws Exception {
        XSquaredGenotype phenoType = new XSquaredGenotype(3, 5);
        int[] expectedBitArray = new int[]{0, 0, 0, 1, 1};
        assertArrayEquals(phenoType.getGenes(), expectedBitArray);
        assertEquals(phenoType.getValue(), 3);
        assertEquals(phenoType.getFitness(), 9);
    }
}
