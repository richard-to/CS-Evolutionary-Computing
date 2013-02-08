package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */
public interface ICrossoverStrategy {
    public List<Genotype> crossover(Genotype genotype1, Genotype genotype2);
}
