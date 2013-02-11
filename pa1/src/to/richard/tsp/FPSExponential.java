package to.richard.tsp;

/**
 * Author: Richard To
 * Date: 2/10/13
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the exponential transform to avoid premature convergence.
 *
 * F' = sqrt(F) + 1
 */
public class FPSExponential implements IFPSTransform {
    @Override
    public List<Pair<Double, Genotype>> transform(List<Pair<Double, Genotype>> genotypeFitnessList) {
        ArrayList<Pair<Double, Genotype>> newList = new ArrayList<Pair<Double, Genotype>>();
        for (Pair<Double, Genotype> pair : genotypeFitnessList) {
            newList.add(new Pair<Double, Genotype>(Math.sqrt(pair.getFirstValue()) + 1, pair.getSecondValue()));
        }
        return newList;
    }
}
