package to.richard.tsp;

import java.util.List;

/**
 * Author: Richard To
 * Date: 2/7/13
 */
public interface ISelector {
    public List<Genotype> selectParents(List<Genotype> genotypes);
}
