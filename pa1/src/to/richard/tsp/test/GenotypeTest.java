package to.richard.tsp.test;

import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import to.richard.tsp.Allele;
import to.richard.tsp.Errors;
import to.richard.tsp.Genotype;
import to.richard.tsp.MutableGenotype;

import java.util.ArrayList;

/**
 * Author: Richard To
 * Date: 2/5/13
 */
public class GenotypeTest {

    @Test
    public void testGetAllele() throws Exception {
        Allele[] alleles = {new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        Genotype genotype = new Genotype(alleles);
        Allele value = genotype.getAllele(3);
        value = new Allele(20);
        alleles[3] = new Allele(50);
        assertEquals(new Allele(3), genotype.getAllele(3));
    }

    @Test
    public void testLength() throws Exception {
        Genotype genotype = new Genotype(new Allele[]{
                new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)});
        assertEquals(5, genotype.length());
    }

    @Test
    public void testCopyMutable() throws Exception {
        Genotype genotype = new Genotype(new Allele[]{
                new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)});
        MutableGenotype mutableGenotype = genotype.copyMutable();
        assertEquals(new Allele(3), mutableGenotype.getAllele(3));
        mutableGenotype.setAllele(new Allele(10), 3);
        assertEquals(new Allele(3), genotype.getAllele(3));
        assertEquals(new Allele(10), mutableGenotype.getAllele(3));
    }

    @Test
    public void testSetAllele() throws Exception {
        MutableGenotype mutableGenotype = new MutableGenotype(3);
        mutableGenotype.setAllele(new Allele(10), 1);
        assertNull(mutableGenotype.getAllele(0));
        assertEquals(new Allele(10), mutableGenotype.getAllele(1));
        mutableGenotype.setAllele(new Allele(20), 1);
        assertEquals(new Allele(20), mutableGenotype.getAllele(1));
    }

    @Test
    public void testCopy() throws Exception {
        MutableGenotype mutableGenotype =
            new MutableGenotype(new Allele[]{
                    new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)});
        Genotype genotype = mutableGenotype.copy();
        mutableGenotype.setAllele(new Allele(10), 1);
        assertEquals(new Allele(1), genotype.getAllele(1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsNegative() {
        Genotype genotype = new Genotype(new Allele[]{
                new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)});
        genotype.getAllele(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsOver() {
        Genotype genotype = new Genotype(new Allele[]{
                new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)});
        genotype.getAllele(20);
    }

    @Test(expected = Errors.AllelesDoNotMatchGenes.class)
    public void testAllelesDoNotMatchGenes() {
        MutableGenotype genotype = new MutableGenotype(new Allele[]{
                new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)});
        genotype.setAlleles(new Allele[]{new Allele(2),new Allele(2)});
    }

    @Test
    public void testGeneIterator() {
        Allele[] expectedAlleles = new Allele[]{
                new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        MutableGenotype genotype = new MutableGenotype(expectedAlleles);

        ArrayList<Allele> visitedAlleles = new ArrayList<Allele>();
        for (Allele allele : genotype) {
            visitedAlleles.add(allele);
        }
        assertEquals(expectedAlleles[4], visitedAlleles.get(4));
    }

    @Test
    public void testFindAllele() {
        Allele[] alleles = {new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        Genotype genotype = new Genotype(alleles);
        int index = genotype.findAllele(new Allele(2));
        assertEquals(new Allele(2), genotype.getAllele(index));
    }

    @Test(expected = Errors.AlleleNotFound.class)
    public void testFindAlleleNotFound() {
        Allele[] alleles = {new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        Genotype genotype = new Genotype(alleles);
        int index = genotype.findAllele(new Allele(10));
    }

    @Test
    public void testGenotypeToString() {
        Allele[] alleles = {new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        Genotype genotype = new Genotype(alleles);
        assertEquals("0-1-2-3-4", genotype.toString());
    }

    @Test
    public void testMutableGenotypeToString() {
        MutableGenotype genotype = new MutableGenotype(3);
        assertEquals("_-_-_", genotype.toString());
    }

    @Test
    public void testHasAllele() {
        Allele[] alleles = {new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        MutableGenotype genotype = new MutableGenotype(3);
        assertEquals(false, genotype.hasAlleleAt(2));
    }

    @Test
    public void testGenotypeEquality() {
        Allele[] alleles = {new Allele(0),new Allele(1),new Allele(2),new Allele(3),new Allele(4)};
        MutableGenotype genotype = new MutableGenotype(3);
        Genotype genotypeSame = genotype.copy();
        assertEquals(genotype, genotypeSame);
    }
}