package to.richard.test;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import to.richard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Richard To
 * Date: 1/29/13
 */

public class ParentSelectorTest {

    @Test
    public void testParentSelector() throws Exception {
        MockRandom random = new MockRandom();
        ArrayList<Double> sequence = new ArrayList<Double>();
        sequence.add(.22);
        sequence.add(.65);
        sequence.add(.35);
        sequence.add(.88);
        random.setDoubleSequence(sequence);

        GenePool genePool = new GenePool(32, 4);
        genePool.addGenotype(new Genotype(20, 5))  //       Fitness: 400   Prob: 34%
                .addGenotype(new Genotype(5, 5))   //       Fitness: 25    Prob: 2%
                .addGenotype(new Genotype(2, 5))   //       Fitness: 4     Prob: 0.3%
                .addGenotype(new Genotype(27, 5)); //       Fitness: 729   Prob: 63%
                                                   // Total Fitness: 1158  Prob: 100%

        ParentSelector parentSelector = new ParentSelector(random);
        List<GenotypeParents> genotypeParentsList = parentSelector.selectParents(genePool, 2);

        GenotypeParents genotypeParents = genotypeParentsList.get(0);
        assertEquals(20, genotypeParents.getParent1().getValue());
        assertEquals(27, genotypeParents.getParent2().getValue());

        genotypeParents = genotypeParentsList.get(1);
        assertEquals(5, genotypeParents.getParent1().getValue());
        assertEquals(27, genotypeParents.getParent2().getValue());
    }
}
