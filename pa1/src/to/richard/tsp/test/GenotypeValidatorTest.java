package to.richard.tsp.test;

import org.junit.Test;
import to.richard.tsp.Allele;
import to.richard.tsp.CostMatrix;
import to.richard.tsp.Genotype;
import to.richard.tsp.GenotypeValidator;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Author: Richard To
 * Date: 2/6/13
 */
public class GenotypeValidatorTest {

    @Test
    public void testValidateTrue() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 23}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new Allele[]{new Allele(1), new Allele(2)});
        GenotypeValidator genotypeValidator = new GenotypeValidator(costMatrix);
        assertEquals(true, genotypeValidator.validate(genotype));
    }

    @Test
    public void testValidateTrueList() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 10}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new Allele[]{new Allele(1), new Allele(2)});
        Genotype genotype2 = new Genotype(new Allele[]{new Allele(2), new Allele(1)});
        GenotypeValidator genotypeValidator = new GenotypeValidator(costMatrix);
        assertEquals(true, genotypeValidator.validate(
                Arrays.asList(new Genotype[]{genotype2, genotype})));
    }

    @Test
    public void testValidateFalseList() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 23}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new Allele[]{new Allele(1), new Allele(0)});
        Genotype genotype2 = new Genotype(new Allele[]{new Allele(0), new Allele(3)});
        GenotypeValidator genotypeValidator = new GenotypeValidator(costMatrix);
        assertEquals(false, genotypeValidator.validate(
                Arrays.asList(new Genotype[]{genotype2, genotype})));
    }

    @Test
    public void testValidateFalseLength() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 23}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new Allele[]{new Allele(1), new Allele(2), new Allele(4)});
        GenotypeValidator genotypeValidator = new GenotypeValidator(costMatrix);
        assertEquals(false, genotypeValidator.validate(genotype));
    }

    @Test
    public void testValidateFalseAllele() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 23}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new Allele[]{new Allele(1), new Allele(3)});
        GenotypeValidator genotypeValidator = new GenotypeValidator(costMatrix);
        assertEquals(false, genotypeValidator.validate(genotype));
    }

    @Test
    public void testValidateFalseDupes() throws Exception {
        int[][] costMatrixArray = {{20, 30, 10}, {40, 21, 24}, {42, 11, 101}};
        CostMatrix costMatrix = new CostMatrix(costMatrixArray);
        Genotype genotype = new Genotype(new Allele[]{new Allele(1), new Allele(1)});
        GenotypeValidator genotypeValidator = new GenotypeValidator(costMatrix);
        assertEquals(false, genotypeValidator.validate(genotype));
    }
}
