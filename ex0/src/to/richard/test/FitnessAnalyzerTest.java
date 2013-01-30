package to.richard.test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import to.richard.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

public class FitnessAnalyzerTest {

    @Test
    public void testFitnessAnalyzer() throws Exception {
        List<Genotype> genotypeList = Arrays.asList(
                new Genotype(2, 5), new Genotype(4, 5),
                new Genotype(8, 5), new Genotype(16, 5));
        FitnessAnalyzer fitnessAnalyzer = new FitnessAnalyzer();
        FitnessResult result = fitnessAnalyzer.analyze(genotypeList);
        assertEquals(340, result.getSum(), 0);
        assertEquals(85, result.getAvg(), 0);
        assertEquals(256, result.getMax(), 0);
    }
}
